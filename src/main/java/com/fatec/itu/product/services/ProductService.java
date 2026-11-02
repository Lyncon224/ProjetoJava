package com.fatec.itu.product.services;

import java.util.List;

import org.springframework.stereotype.Service;


import com.fatec.itu.product.dtos.ProductRequest;
import com.fatec.itu.product.dtos.ProductResponse;
import com.fatec.itu.product.entities.Product;

import com.fatec.itu.product.mappers.ProductMapper;
import com.fatec.itu.product.repositories.ProductRepository;

import jakarta.persistence.EntityNotFoundException;

// @Service: indica ao Spring que esta classe pertence à camada de Serviço (regras de negócio)
// É ela que faz o meio de campo entre o Controller (que recebe DTOs) e o Repository (que salva Entidades no banco).
@Service
public class ProductService {
     private final ProductRepository repository;
    //construtor: o Spring faz a injeção de dependência do ProductRepository automaticamente aqui
    ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    //metodo - passa o id e devolve o produto se não um erro 
    // Busca a entidade no banco pelo ID, converte para ProductResponse (DTO) usando o ProductMapper, ou lança erro se não existir
  public ProductResponse findById(Long id) {
        return repository.findById(id)
                .map(ProductMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Produto não cadastrado"));
    }

    // Busca a lista de todas as entidades Product no banco e converte cada uma para ProductResponse (DTO) usando Stream
    public List<ProductResponse> findAll(){
        return repository.findAll().stream()
                .map(ProductMapper::toDTO)
                .toList();
    }

    //delete por id 
    //se o id existir delete se não "não econtrado"
    public void deleteById(long id) {

            if(repository.existsById(id))
            repository.deleteById(id);
            else
           throw new EntityNotFoundException("Produto não cadastrado");
    }

    // Recebe ProductRequest e devolve ProductResponse
    // 1. Converte o DTO de requisição (ProductRequest) em Entidade (Product)
    // 2. Salva no banco de dados (que gera o ID)
    // 3. Converte a entidade salva com ID para ProductResponse e retorna
    public ProductResponse save(ProductRequest product)
    {
        Product p = repository.save(ProductMapper.toEntity(product));
        return ProductMapper.toDTO(p);
    }


    // Atualiza um produto existente: busca pelo ID no banco, altera os campos com os dados do DTO e salva novamente
     public void update(ProductRequest product, Long id)
    {
        Product p  = repository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Produto não cadastrado"));

        p.setDescription(product.description());                                
        p.setName(product.name());
        p.setPrice(product.price());

        repository.save(p);


    }
}
