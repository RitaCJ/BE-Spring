package com.example.bespring.domainTests;

import com.example.bespring.domain.Aluno;
import com.example.bespring.domain.Escola;
import com.example.bespring.domain.Utilizador;
import com.example.bespring.domain.enums.Genero;
import com.example.bespring.domain.enums.Perfil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AlunoDomainTest {

    @Test
    void deveCriarAlunoComCamposValidos()  {

        String nomeUtilizador = "asaj01";
        int numeroAluno = 5;
        String sala = "1A";
        String corFavorita = "Azul";
        boolean possuiDaltonismo = true;

        String primeiroNome = "Ana";
        String sobrenome = "Asa";
        Genero genero = Genero.FEMININO;
        Perfil perfil = Perfil.ALUNO;

        Escola escola = new Escola("School", "Rua francisco da silva", "9234234445", "school@gmail.com");

        Aluno aluno = new Aluno(primeiroNome, sobrenome, nomeUtilizador, numeroAluno, sala, corFavorita, possuiDaltonismo, genero, escola, perfil);

        assertEquals(primeiroNome, aluno.getPrimeiroNome());
        assertEquals(sobrenome, aluno.getSobrenome());
        assertEquals(nomeUtilizador, aluno.getNomeUtilizador());
        assertEquals(numeroAluno, aluno.getNumeroAluno());
        assertEquals(sala, aluno.getSala());
        assertEquals(corFavorita, aluno.getCorFavorita());
        assertEquals(possuiDaltonismo, aluno.isPossuiDaltonismo());
        assertEquals(genero, aluno.getGenero());
        assertEquals(escola, aluno.getEscola());
        assertEquals(perfil, aluno.getPerfil());
    }
}
