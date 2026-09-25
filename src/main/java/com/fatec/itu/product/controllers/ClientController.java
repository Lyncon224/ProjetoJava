package com.fatec.itu.product.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fatec.itu.product.entities.Client;
import com.fatec.itu.product.services.ClientService;



@RestController
@RequestMapping("/client")
public class ClientController {
    private final ClientService service ;

    //injeção do serviço 
    ClientController(ClientService service){
        this.service = service;
    }

    //exposição por id 1- end point 
    @GetMapping("{id}")
    public ResponseEntity<Client> getById (@PathVariable long id){
        return ResponseEntity.ok(service.findById(id));
    }

    //retornar tudo 
    @GetMapping
    public ResponseEntity<List<Client>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    




    













        //mapeado como delete
        //delete mapeando por id
        @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }


    //metodo post, passa um produto na requisição
     @PostMapping
    public ResponseEntity<Client> save(@RequestBody Client client)
    {
        Client c = service.save(client);
       
        //localização do cliente
        URI location = ServletUriComponentsBuilder
                       .fromCurrentRequest()
                       .path("/{id}")
                       .buildAndExpand(c.getId())
                       .toUri();
        
        return ResponseEntity.created(location).body(c);
                            //status code 201

    }

    @PutMapping("{id}")
    
    public ResponseEntity<Void> update(@PathVariable long id,
    @RequestBody Client client)
    {
    service.update(client, id);
    return ResponseEntity.noContent().build();
    }

    
}
