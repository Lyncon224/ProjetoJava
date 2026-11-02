package com.fatec.itu.product.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// @Entity: mapeia esta classe como uma entidade gerenciada pelo JPA/Hibernate
@Entity
// @Table: especifica que os dados serão gravados na tabela TBL_CLIENT no banco H2
@Table(name="TBL_CLIENT")
public class Client implements java.io.Serializable  {
    
    // @Id e @GeneratedValue: definem a chave primária gerada automaticamente pelo banco (auto-incremento)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String cidade;
    private int idade;
    private double altura;
    
    // Getters e Setters para encapsulamento dos atributos da entidade
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
    public String getCidade() {
        return cidade;
    }
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
    public Double getAltura() {
        return altura;
    }
    public void setAltura(Double altura) {
        this.altura = altura;
    }

        public int getIdade() {
        return idade;
    }
    public void setIdade(int idade) {
        this.idade = idade;
    }

    // equals: compara dois objetos Client utilizando o ID como critério de igualdade
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Client other = (Client) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
