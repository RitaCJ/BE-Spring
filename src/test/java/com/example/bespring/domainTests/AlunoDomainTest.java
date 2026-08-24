package com.example.bespring.domainTests;

import com.example.bespring.domain.Utilizador;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aluno extends Utilizador {

    @Test
    void deveCriarAlunoComCamposValidos()  {

        String nomeUtilizador = "asaj01";
        int numeroAluno = 5;
        String sala = "1A";
        String corFavorita = "Azul";
        boolean possuiDaltonismo = true;

        Aluno aluno = new Aluno(nomeUtilizador, numeroAluno, sala, corFavorita, possuiDaltonismo);

        assertEquals(nomeUtilizador, aluno.getNomeUtilizador());
        assertEquals(numeroAluno, aluno.getNumeroAluno());
        assertEquals(sala, aluno.getSala());
        assertEquals(corFavorita, aluno.gertCorFavorita());
        assertEquals(possuiDaltonismo, aluno.getPossuiDaltonismo());

    }

}
