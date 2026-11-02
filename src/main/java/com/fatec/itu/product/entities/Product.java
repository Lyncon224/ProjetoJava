package com.fatec.itu.product.entities;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// @Entity: indica ao JPA/Hibernate que esta classe representa uma tabela no banco de dados
@Entity
// @Table: define o nome da tabela no banco de dados (TBL_PRODUCT)
@Table(name="TBL_PRODUCT")


public class Product implements java.io.Serializable {

    //com private somente dentro da classe que da pra ler 
    // @Id: define este atributo como a chave primária (Primary Key) da tabela
    @Id
    // @GeneratedValue: configura o banco para gerar o ID automaticamente (auto-incremento)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Double price;

    //como os atributos são privados os metodos tem que ser publicos 
    // Getters e Setters: permitem ler (get) e alterar (set) os atributos privados da entidade
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }

    // hashCode e equals: usados para comparar se dois objetos Product representam o mesmo registro (pelo ID)
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Product other = (Product) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    
    
}
