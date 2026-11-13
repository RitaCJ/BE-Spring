package com.example.bespring.modulosTest.utilizadoresTest.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Escola {

    /**
     * @OneToMany - Flag -> Uma escola para muitos utilizadores. A escola tem uma lista de utilizadores.
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEscola;

    @NotEmpty
    @Column(length = 40, nullable = false)
    private String nome;

    @NotEmpty
    @Column(length = 60, nullable = false)
    private String endereco;

    @NotEmpty
    @Column(length = 9, nullable = false)
    private String telefone;

    @NotEmpty
    @Column(length = 25, nullable = false)
    private String email;

    //Admin que registou a escola
    @ManyToOne(optional = false)
    @JoinColumn(name = "administrador_id", nullable = false)
    private Utilizador administrador;

    //@NotEmpty
    @OneToMany(mappedBy = "escola")
    private List<Utilizador> utilizadores;


    /**
     * Construtor da classe Escola
     * @param nome - nome da escola
     * @param endereco - endereço da escola
     * @param telefone - telefone da escola
     * @param email - email da escola
     */
    public Escola(String nome, String endereco, String telefone, String email) {

        if(nome == null || nome.isBlank()){
            throw new IllegalArgumentException("O nome não pode ser vázio (null)");
        }else if(endereco == null || endereco.isBlank()){
            throw new IllegalArgumentException("O endereço não pode ser vázio (null)");
        }else if(telefone == null || telefone.isBlank()){
            throw new IllegalArgumentException("O telefone não pode ser vázio (null)");
        }else if(email == null || email.isBlank()){
            throw new IllegalArgumentException("O email não pode ser vázio (null)");
        }else if(!telefone.matches("[0-9]{9}$")){
            throw new IllegalArgumentException("O telefone deve conter: 9 dígitos e não pode ter letras");
        }

        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
    }

    public Escola() {

    }


}
