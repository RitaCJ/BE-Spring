package com.example.bespring.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Turma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTurma;

    @NotEmpty
    @Column(length = 7, nullable = false)
    private String nome;

    @NotEmpty
    @Column(length = 5, nullable = false)
    private String sala;

    @NotEmpty
    @Min(2000)
    @Max(2100)
    private int anoLetivo;

    @NotEmpty
    @Column(length = 7, nullable = false)
    private String anoSerie;

    @NotEmpty
    @Column(length = 80, nullable = false)
    private String descricao;

    @NotEmpty
    @OneToMany(mappedBy = "turma")
    private List<Aluno> alunos;


    public Turma() {

    }

    public Turma(String nome, String sala, int anoLetivo, String anoSerie, String descricao) {

        if(nome == null || nome.isBlank()){
            throw new IllegalArgumentException("O campo nome não deve estar vázio.");
        }else if(sala == null || sala.isBlank()){
            throw new IllegalArgumentException("O campo sala não deve estar vázio.");
        }else if(anoLetivo < 2000 || anoLetivo > 2100){
            throw new IllegalArgumentException("O ano letivo deve ser entre 2000 e 2100.");
        }else if(anoSerie == null || anoSerie.isBlank()){
            throw new IllegalArgumentException("O campo ano/Serie não deve estar vázio.");
        }else if(descricao == null || descricao.isBlank()){
            throw new IllegalArgumentException("O campo descrição não deve estar vázio.");
        }

        this.nome = nome;
        this.sala = sala;
        this.anoLetivo = anoLetivo;
        this.anoSerie = anoSerie;
        this.descricao = descricao;
    }




}
