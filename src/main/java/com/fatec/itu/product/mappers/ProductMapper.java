package com.fatec.itu.product.mappers;

import com.fatec.itu.product.dtos.ProductRequest;
import com.fatec.itu.product.entities.Product;
import com.fatec.itu.product.dtos.ProductResponse;

// Classe Mapper (Tradutora): responsável por converter objetos entre DTO e Entidade (Product).
// Seus métodos são "static" para podermos chamar diretamente (ex: ProductMapper.toDTO(product)) sem precisar dar "new ProductMapper()".
public class ProductMapper {
    
    //Tranforma o DTO em entidade para salvar no banco de dados
    //DTO = Data Transfer Object (objeto que transporta dados entre processos), 
    //servindo para encapsular os dados e reduzir o número de chamadas de métodos.
    public static Product toEntity(ProductRequest request){
        Product p = new Product();
        p.setName(request.name());
        p.setDescription(request.description());
        p.setPrice(request.price());
        return p;
    }

    //Tranforma a entidade em DTO para enviar como resposta (JSON)
    public static ProductResponse toDTO(Product product){
        return new ProductResponse(
            product.getId(),
            product.getName(),
            product.getDescription(),
            product.getPrice()
        );
    }
}
