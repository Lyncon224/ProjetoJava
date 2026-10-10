package com.fatec.itu.product.dtos;

public record ClientRequest(
    String name,
    String cidade,
    int idade,
    double altura
) {
}
