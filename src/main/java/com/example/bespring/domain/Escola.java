package com.example.bespring.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Escola {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEscola;

    @NotEmpty
    @Column(length = 25, nullable = false)
    private String nome;

    @NotEmpty
    @Column(length = 30, nullable = false)
    private String endereco;

    @NotEmpty
    @Column(length = 12, nullable = false)
    private String telefone;

    @NotEmpty
    @Column(length = 25, nullable = false)
    private String email;

    @NotEmpty
    //Flag - Uma escola para muitos utilizadores. A escola tem uma lista de utilizadores.
    @OneToMany(mappedBy = "escola")
    private List<Utilizador> utilizadores;

    //Construtor da classe Escola
    public Escola(String nome, String endereco, String telefone, String email) {

        if(nome == null || nome.isBlank()){
            throw new IllegalArgumentException("O nome não pode ser vázio (null)");
        }else if(endereco == null || endereco.isBlank()){
            throw new IllegalArgumentException("O endereço não pode ser vázio (null)");
        }else if(telefone == null || telefone.isBlank()){
            throw new IllegalArgumentException("O telefone não pode ser vázio (null)");
        }else if(email == null || email.isBlank()){
            throw new IllegalArgumentException("O email não pode ser vázio (null)");
        }

        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
    }

    public Escola() {

    }


}
