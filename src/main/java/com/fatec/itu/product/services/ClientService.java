package com.fatec.itu.product.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatec.itu.product.dtos.ClientRequest;
import com.fatec.itu.product.dtos.ClientResponse;
import com.fatec.itu.product.entities.Client;
import com.fatec.itu.product.mappers.ClientMapper;
import com.fatec.itu.product.repositories.ClientRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    // Retorna todos os clientes
    public List<ClientResponse> findAll() {
        return repository.findAll()
                         .stream()
                         .map(ClientMapper::toDTO)
                         .toList();
    }

    // Retorna cliente por ID
    public ClientResponse findById(Long id) {
        return repository.findById(id)
                         .map(ClientMapper::toDTO)
                         .orElseThrow(() -> new EntityNotFoundException("Cliente não cadastrado"));
    }

    // Deleta por id
    public void deleteById(long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Cliente não cadastrado");
        }
    }

    // Salva novo cliente
    public ClientResponse save(ClientRequest client) {
        Client c = repository.save(ClientMapper.toEntity(client));
        return ClientMapper.toDTO(c);
    }

    // Atualiza cliente existente
    public void update(ClientRequest client, Long id) {
        Client c = repository.findById(id)
                             .orElseThrow(() -> new EntityNotFoundException("Cliente não cadastrado"));

        c.setName(client.name());
        c.setCidade(client.cidade());
        c.setIdade(client.idade());
        c.setAltura(client.altura());

        repository.save(c);
    }
}
