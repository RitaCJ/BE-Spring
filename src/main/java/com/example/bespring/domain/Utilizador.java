package com.example.bespring.domain;

import com.example.bespring.domain.enums.Genero;
import com.example.bespring.domain.enums.Perfil;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED) //Será feita uma junção através das chaves estrangeiras.
public class Utilizador {

    //Defini a chave primária
    @Id
    //Faz o ID ser auto-incrementavel
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUtilizador;

    @NotEmpty
    @Column(length = 15, nullable = false)
    private String primeiroNome;

    @NotEmpty
    @Column(length = 15, nullable = false)
    private String sobrenome;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Genero genero;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Perfil perfil;

    
    @ManyToOne //Flag - Muitos utilizadores para uma escola.
    @JoinColumn(name = "escola_id", nullable = false)
    private Escola escola;

    public Utilizador() {

    }

    public Utilizador(String primeiroNome, String sobrenome, Genero genero, Perfil perfil, Escola escola) {

        if(primeiroNome == null || primeiroNome.isBlank()){
            throw new IllegalArgumentException("O primeiro nome não pode ser vázio");
        }else if(sobrenome == null || sobrenome.isBlank()){
            throw new IllegalArgumentException("O sobrenome não pode ser vázio");
        }

        this.primeiroNome = primeiroNome;
        this.sobrenome = sobrenome;
        this.genero = genero;
        this.perfil = perfil;
        this.escola = escola;
    }


}
