package com.fatec.itu.product.mappers;

import com.fatec.itu.product.dtos.ClientRequest;
import com.fatec.itu.product.dtos.ClientResponse;
import com.fatec.itu.product.entities.Client;

public class ClientMapper {

    public static Client toEntity(ClientRequest request) {
        Client c = new Client();
        c.setName(request.name());
        c.setCidade(request.cidade());
        c.setIdade(request.idade());
        c.setAltura(request.altura());
        return c;
    }

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
