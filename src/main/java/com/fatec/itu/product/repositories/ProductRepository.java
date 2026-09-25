package com.fatec.itu.product.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import com.fatec.itu.product.entities.Product;

//o repositorio que vai executar o CRUD basico; ponte para o banco de dados ;
public interface ProductRepository extends JpaRepository<Product, Long> {
    
}
