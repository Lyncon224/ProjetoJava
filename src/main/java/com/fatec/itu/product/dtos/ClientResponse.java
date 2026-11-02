package com.fatec.itu.product.dtos;

// DTO de Resposta (Saída): representa os dados do cliente que a API retorna no JSON (GET ou POST).
// Contém o "id" gerado pelo banco de dados além dos demais atributos do cliente.
public record ClientResponse(
    Long id,
    String name,
    String cidade,
    int idade,
    double altura
) {
}
