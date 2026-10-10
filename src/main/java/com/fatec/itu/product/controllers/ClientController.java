package com.fatec.itu.product.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.fatec.itu.product.dtos.ClientRequest;
import com.fatec.itu.product.dtos.ClientResponse;
import com.fatec.itu.product.services.ClientService;

@RestController
@RequestMapping({"/clients", "/client"})
public class ClientController {

    @Autowired
    private ClientService service;

    // Retorna todos os clientes
    @GetMapping
    public ResponseEntity<List<ClientResponse>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    // Retorna cliente por ID
    @GetMapping("{id}")
    public ResponseEntity<ClientResponse> getById(@PathVariable long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    // Deleta cliente por ID
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // Cadastra cliente
    @PostMapping
    public ResponseEntity<ClientResponse> save(@RequestBody ClientRequest client) {
        ClientResponse c = service.save(client);

        URI location = ServletUriComponentsBuilder
                       .fromCurrentRequest()
                       .path("/{id}")
                       .buildAndExpand(c.id())
                       .toUri();

        return ResponseEntity.created(location).body(c);
    }

    // Atualiza cliente por ID
    @PutMapping("{id}")
    public ResponseEntity<Void> update(@PathVariable long id,
                                       @RequestBody ClientRequest client) {
        service.update(client, id);
        return ResponseEntity.noContent().build();
    }
}
