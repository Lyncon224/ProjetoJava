package com.fatec.itu.product.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fatec.itu.product.entities.Client;

import com.fatec.itu.product.repositories.ClientRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ClientService {
    private final ClientRepository repository;

    //construtor 
    ClientService (ClientRepository repository) {
        this.repository = repository;
    }
    //Retorna by ID 
    public Client findById(Long id) {
        return repository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException());
    }

    public List <Client> findAll(){
       
        return repository.findAll(); //recuperar todos 
    }


    //delete por id 
    //se o id existir delete se não "não econtrado"
    public void deleteById(long id) {

            if(repository.existsById(id))
            repository.deleteById(id);
            else
           throw new EntityNotFoundException("Produto não cadastrado");
    }

    public Client save(Client client)
    {
         return repository.save(client);
    }


    
     public void update(Client client, Long id)
    {
        Client c  = repository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Cliente não cadastrado"));

        c.setAltura(client.getAltura());                                
        c.setName(client.getName());
        c.setCidade(client.getCidade());
        c.setIdade(client.getIdade());

        repository.save(c);


    }
}
