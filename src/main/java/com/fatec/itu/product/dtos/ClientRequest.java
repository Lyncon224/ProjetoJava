package com.fatec.itu.product.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

//Tem apenas os dados que o cliente envia para cadastrar/atualizar (sem id).
// As anotações do Bean Validation (@NotBlank, @Min) validam os dados automaticamente quando o Controller usa @Valid.
public record ClientRequest(
   
    // @NotBlank: impede que o nome seja nulo ou vazio
    @NotBlank(message = "Name is required")
    String name,

    // @NotBlank: impede que a cidade seja nula ou vazia
    @NotBlank(message = "City is required")
    String cidade,

    // @Min(0): garante que a idade não seja um número negativo
    @Min(value = 0, message = "Age must be a positive number")
    int idade,

    // @Min(0): garante que a altura não seja um número negativo
    @Min(value = 0, message = "Height must be a positive number")
    double altura
) {
}
