package com.fatec.itu.product.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fatec.itu.product.dtos.ClientRequest;
import com.fatec.itu.product.dtos.ClientResponse;
import com.fatec.itu.product.services.ClientService;

import jakarta.validation.Valid;

// @RestController: define que esta classe é um controlador REST (recebe requisições HTTP e responde em JSON)
@RestController
// @RequestMapping("/client"): define o endereço base dos endpoints de cliente (http://localhost:8080/client)
@RequestMapping("/client")
// @CrossOrigin: libera o acesso para chamadas vindas de outros domínios/portas (como um front-end Angular/React)
@CrossOrigin
public class ClientController {
    private final ClientService service;

    // Injeção de dependência do ClientService via construtor
    ClientController(ClientService service) {
        this.service = service;
    }

    // Endpoint GET por ID (http://localhost:8080/client/{id}): busca um único cliente e retorna status 200 (OK)
    @GetMapping("{id}")
    public ResponseEntity<ClientResponse> getById(@PathVariable long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    // Endpoint GET (http://localhost:8080/client): retorna a lista de todos os clientes em formato ClientResponse
    @GetMapping
    public ResponseEntity<List<ClientResponse>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    // Endpoint DELETE (http://localhost:8080/client/{id}): remove o cliente pelo ID e retorna status 204 (No Content)
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // Endpoint POST (http://localhost:8080/client): cadastra um novo cliente
    // @Valid valida os campos do ClientRequest (@NotBlank, @Min) antes de executar o método
    @PostMapping
    public ResponseEntity<ClientResponse> save(@Valid @RequestBody ClientRequest client) {
        ClientResponse c = service.save(client);

        // Cria a URI (cabeçalho Location) apontando para o endereço do novo cliente criado (ex: /client/2)
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(c.id()) // Usa c.id() do Record
                .toUri();

        // Retorna status 201 (Created), o cabeçalho Location e os dados do cliente salvo no corpo
        return ResponseEntity.created(location).body(c);
    }

    // Endpoint PUT (http://localhost:8080/client/{id}): atualiza os dados de um cliente existente
    // Recebe o ID na URL (@PathVariable) e os novos dados validados no corpo da requisição (@Valid @RequestBody)
    @PutMapping("{id}")
    public ResponseEntity<Void> update(@PathVariable long id,
    @Valid  @RequestBody ClientRequest client) {
        service.update(client, id);
        return ResponseEntity.noContent().build();
    }
}
