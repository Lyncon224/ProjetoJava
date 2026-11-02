package com.fatec.itu.product.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fatec.itu.product.dtos.ClientRequest;
import com.fatec.itu.product.dtos.ClientResponse;
import com.fatec.itu.product.entities.Client;
import com.fatec.itu.product.mappers.ClientMapper;
import com.fatec.itu.product.repositories.ClientRepository;

import jakarta.persistence.EntityNotFoundException;

// @Service: define a classe como um componente de Serviço do Spring (onde ficam as regras de negócio de clientes)
@Service
public class ClientService {
    private final ClientRepository repository;

    //construtor: injeta o ClientRepository para podermos acessar o banco de dados
    ClientService (ClientRepository repository) {
        this.repository = repository;
    }
    //Retorna by ID 
    // Procura o cliente no banco pelo ID; se encontrar, converte para ClientResponse (DTO); se não, lança exceção
    public ClientResponse findById(Long id) {
        return repository.findById(id)
                .map(ClientMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Cliente não cadastrado"));
    }

    //Retorna todos os clientes cadastrados; Antes retornava List<Client> -> Agora retorna List<ClientResponse>
    public List<ClientResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(ClientMapper::toDTO)
                .toList();
    }


    //delete por id 
    //se o id existir delete se não "não econtrado"
    public void deleteById(long id) {

            if(repository.existsById(id))
            repository.deleteById(id);
            else
           throw new EntityNotFoundException("Cliente não cadastrado");
    }


    // Recebe ClientRequest e devolve ClientResponse
    // Converte o DTO recebido para Entidade Client, salva no banco e devolve o ClientResponse com o ID gerado
    public ClientResponse save(ClientRequest client) {
        Client c = repository.save(ClientMapper.toEntity(client));
        return ClientMapper.toDTO(c);
    }


    
    // Recebe ClientRequest
    // Busca o cliente pelo ID, atualiza seus atributos com os valores vindos do DTO (record) e salva no banco
    public void update(ClientRequest client, Long id) {
        Client c = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente não cadastrado"));
        c.setAltura(client.altura());
        c.setName(client.name());
        c.setCidade(client.cidade());
        c.setIdade(client.idade());
        repository.save(c);
    }
}
