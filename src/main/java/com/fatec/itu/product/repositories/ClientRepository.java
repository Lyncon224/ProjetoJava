package com.fatec.itu.product.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import com.fatec.itu.product.entities.Client;

//o repositorio que vai executar o CRUD basico e tudo relacionado ao banco de dados j avem tuudo pronto do spring 
public interface ClientRepository extends JpaRepository<Client, Long> {
    
}

