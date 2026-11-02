package com.fatec.itu.product.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import com.fatec.itu.product.entities.Product;

//o repositorio que vai executar o CRUD basico; ponte para o banco de dados ;
// Ao estender JpaRepository<Product, Long>, o Spring Data JPA fornece automaticamente métodos prontos como:
// findAll(), findById(), save(), deleteById(), existsById(), etc., sem precisarmos escrever SQL.
public interface ProductRepository extends JpaRepository<Product, Long> {
    
}
