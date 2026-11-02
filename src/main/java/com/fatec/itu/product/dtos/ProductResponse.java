package com.fatec.itu.product.dtos;

// DTO de Resposta (Saída): representa os dados que a API devolve em formato JSON para quem fez a requisição (GET ou POST).
// Inclui o "id" gerado pelo banco de dados para que o cliente/front-end possa identificar o produto.
public record ProductResponse(
    Long id,
    String name,
    String description,
    Double price
) {
    
}
