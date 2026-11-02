package com.fatec.itu.product.controllers;
import java.net.URI;
//importar o correto, ficar ligado!!!!!!!!!!!!!!!!!!
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import jakarta.validation.Valid;

import com.fatec.itu.product.dtos.ProductRequest;
import com.fatec.itu.product.dtos.ProductResponse;
import com.fatec.itu.product.services.ProductService;
//as chamadas http são feitas no controller 

// @RestController: indica que esta classe responde a requisições HTTP REST retornando dados em JSON
@RestController
// @RequestMapping("/products"): define a rota base (http://localhost:8080/products)
@RequestMapping("/products")
// @CrossOrigin: permite que aplicações front-end (em outras portas/domínios) acessem esta API
@CrossOrigin
public class ProductController {
  private final ProductService service;

    //injeção do serviço 
    ProductController(ProductService service) {
        this.service = service;
    }
    //exposição por id endpoint (GET http://localhost:8080/products/{id})
        @GetMapping("{id}")
    public ResponseEntity<ProductResponse> getById(@PathVariable long id){
        // Retorna status 200 (OK) com o DTO ProductResponse no corpo da resposta
        return ResponseEntity.ok(service.findById(id));
    }
    //getmapping para recuperar um recurso
    //exposição http de tods os produtos endpoint (GET http://localhost:8080/products)
        @GetMapping
    public ResponseEntity<List<ProductResponse>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }
    //retonar tudo de uma vez da bst na vida real pq retornar milhares de uma vez explode a merda toda
    //taca paginação pra ir buscando pouco a pouco !!!!!!!!!!!

        //mapeado como delete
        //delete mapeando por id (DELETE http://localhost:8080/products/{id})
        @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id) {
        service.deleteById(id);
        // Retorna status 204 (No Content) indicando que foi excluído com sucesso e não há conteúdo de retorno
        return ResponseEntity.noContent().build();
    }


    //metodo post, passa um produto na requisição (POST http://localhost:8080/products)
    // @Valid: aciona as validações definidas no ProductRequest (@NotBlank, @Size, @Min)
    // @RequestBody: converte o JSON enviado no corpo da requisição para o record ProductRequest
     @PostMapping
    public ResponseEntity<ProductResponse> save(@Valid @RequestBody ProductRequest product)
    {
        ProductResponse p = service.save(product);
       
        //localização do produto: monta a URL do novo recurso criado (ex: http://localhost:8080/products/4)
        URI location = ServletUriComponentsBuilder
        .fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(p.id())
        .toUri();
        
        return ResponseEntity.created(location).body(p);
        //status code 201 (Created)

    }

    // Atualiza um produto existente pelo ID (PUT http://localhost:8080/products/{id})
    @PutMapping("{id}")
    
    public ResponseEntity<Void> update( @PathVariable long id,@Valid @RequestBody
     ProductRequest product)
    {
    service.update(product, id);
    // Retorna status 204 (No Content) após atualizar com sucesso
    return ResponseEntity.noContent().build();
    }


}
