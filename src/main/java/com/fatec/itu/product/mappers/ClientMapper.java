package com.fatec.itu.product.mappers;

import com.fatec.itu.product.dtos.ClientRequest;
import com.fatec.itu.product.dtos.ClientResponse;
import com.fatec.itu.product.entities.Client;

// Classe Mapper (Tradutora): faz a conversão entre os DTOs (ClientRequest / ClientResponse) e a Entidade (Client) do banco de dados.
public class ClientMapper {

    // 1. Transforma o DTO que veio da requisição em Entidade para salvar no banco
    public static Client toEntity(ClientRequest request) {
        Client c = new Client();
        c.setName(request.name());
        c.setCidade(request.cidade());
        c.setIdade(request.idade());
        c.setAltura(request.altura());
        return c;
    }

    // 2. Transforma a Entidade que veio do banco em DTO de resposta (JSON)
    public static ClientResponse toDTO(Client client) {
        return new ClientResponse(
            client.getId(),
            client.getName(),
            client.getCidade(),
            client.getIdade(),
            client.getAltura()
        );
    }
}
