package com.fatec.itu.product.controllers;
import java.net.URI;
//importar o correto, ficar ligado!!!!!!!!!!!!!!!!!!
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

import com.fatec.itu.product.entities.Product;
import com.fatec.itu.product.services.ProductService;
//as chamadas http são feitas no controller 

@RestController
@RequestMapping("/products")
public class ProductController {
  private final ProductService service;

    //injeção do serviço 
    ProductController(ProductService service) {
        this.service = service;
    }
    //exposição por id endpoint 
        @GetMapping("{id}")
    public ResponseEntity<Product> getById(@PathVariable long id){
        return ResponseEntity.ok(service.findById(id));
    }
    //getmapping para recuperar um recurso
    //exposição http de tods os produtos endpoint 
        @GetMapping
    public ResponseEntity<List<Product>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }
    //retonar tudo de uma vez da bst na vida real pq retornar milhares de uma vez explode a merda toda
    //taca paginação pra ir buscando pouco a pouco !!!!!!!!!!!

        //mapeado como delete
        //delete mapeando por id
        @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }


    //metodo post, passa um produto na requisição
     @PostMapping
    public ResponseEntity<Product> save(@RequestBody Product product)
    {
        Product p = service.save(product);
       
        //localização do produto
        URI location = ServletUriComponentsBuilder
                       .fromCurrentRequest()
                       .path("/{id}")
                       .buildAndExpand(p.getId())
                       .toUri();
        
        return ResponseEntity.created(location).body(p);
                            //status code 201

    }

    @PutMapping("{id}")
    
    public ResponseEntity<Void> update(@PathVariable long id,
    @RequestBody Product product)
    {
    service.update(product, id);
    return ResponseEntity.noContent().build();
    }


}