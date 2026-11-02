package com.fatec.itu.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// @SpringBootApplication: anotação principal que configura automaticamente o Spring Boot,
// escaneia os pacotes em busca de @Entity, @Repository, @Service e @RestController e sobe o servidor Tomcat na porta 8080.
@SpringBootApplication
public class ProductApplication {

	// Método principal (ponto de entrada) que inicia a aplicação Java
	public static void main(String[] args) {
		SpringApplication.run(ProductApplication.class, args);
	}

}
