package com.fatec.itu.product.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

// DTO de Requisição (Entrada): representa os dados enviados pelo cliente (JSON) ao criar (POST) ou atualizar (PUT) um produto.
// Não possui o campo "id", pois o id é gerado automaticamente pelo banco de dados.
// Utilizamos "record" do Java, que já cria construtor, métodos de acesso (name(), description(), price()), equals, hashCode e toString automaticamente.
public record ProductRequest( 
   
    // @NotBlank: garante que o nome não seja nulo nem vazio (apenas espaços em branco)
    @NotBlank(message = "Name is required")
    // @Size: define o tamanho mínimo e máximo de caracteres permitidos
    @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
    String name,

    // @NotBlank e @Size: validam que a descrição foi preenchida e respeita o limite de caracteres
    @NotBlank(message = "Description is required")
    @Size(min = 5, max = 500, message = "Description must be between 5 and 500 characters")
    String description,
    
    // @Min: garante que o preço não seja negativo (mínimo 0)
    @Min(value = 0, message = "Price must be greater than or equal to 0")
    Double price) {
}
