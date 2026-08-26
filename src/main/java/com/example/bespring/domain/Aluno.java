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
@PrimaryKeyJoinColumn(name = "idUtilizador") //Identifica qual campo fará essa junção entre a tabela aluno e a tabela utilizador.
public class Aluno extends Utilizador {

   //Aluno herda de utilizador. Não poder ter um id.

    @NotEmpty
    @Column(length = 15, nullable = false)
    private String nomeUtilizador;

    @NotEmpty
    @Column(nullable = false)
    private int numeroAluno;

    @NotEmpty
    @Column(length = 5, nullable = false)
    private String sala;

    @NotEmpty
    @Column(length = 20, nullable = false)
    private String corFavorita;

    @NotEmpty
    @Column(nullable = false)
    private boolean possuiDaltonismo;

    @NotEmpty
    @ManyToOne
    @JoinColumn(name = "id_turma", nullable = true)//Aluno não é obrigado a estar associado a uma turma.
    private Turma turma;

    public Aluno() {

    }

    public Aluno(String primeiroNome, String sobrenome, String nomeUtilizador, int numeroAluno, String sala, String corFavorita, Boolean possuiDaltonismo, Genero genero, Escola escola, Perfil perfil, Turma turma) {

        if(primeiroNome == null || primeiroNome.isBlank()){
            throw new IllegalArgumentException("O primeiro nome não pode ser vázio");
        }else if(sobrenome == null || sobrenome.isBlank()){
            throw new IllegalArgumentException("O sobrenome não pode ser vázio");
        }else if(nomeUtilizador == null || nomeUtilizador.isBlank()){
            throw new IllegalArgumentException("O nome de utilizador não pode ser vázio");
        }else if(sala == null || sala.isBlank()){
            throw new IllegalArgumentException("O campo sala não pode ser vázio");
        }else if(corFavorita == null || corFavorita.isBlank()){
            throw new IllegalArgumentException("O campo cor favorita não pode ser vázio");
        }

        this.setPrimeiroNome(primeiroNome); //Campo herdado
        this.setSobrenome(sobrenome);
        this.nomeUtilizador = nomeUtilizador;
        this.numeroAluno = numeroAluno;
        this.sala = sala;
        this.corFavorita = corFavorita;
        this.possuiDaltonismo = possuiDaltonismo;
        this.setGenero(genero);
        this.setEscola(escola); //escola - variavel ou parametro
        this.setPerfil(Perfil.ALUNO); //Perfil - enum
        this.turma = turma;

    }


}
