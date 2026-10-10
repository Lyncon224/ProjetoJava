package com.fatec.itu.product.dtos;

public record ClientResponse(
    Long id,
    String name,
    String cidade,
    int idade,
    double altura
) {
}
