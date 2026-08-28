package com.example.bespring.domainTests;

import com.example.bespring.domain.*;
import com.example.bespring.domain.enums.Genero;
import com.example.bespring.domain.enums.Perfil;
import com.example.bespring.domain.enums.TipoProfissional;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

        Professor professor = new Professor("Bruna", "Asa", "993838844", Genero.FEMININO, "bruna@gmail.com", "@Bemvindo123", TipoProfissional.PROFESSOR,  Perfil.PROFESSOR, escola);

        Turma turma = new Turma("Flor", "4B", 2026, "3º", "Flores verdes", professor);

        Aluno aluno = new Aluno(primeiroNome, sobrenome, nomeUtilizador, numeroAluno, sala, corFavorita, possuiDaltonismo, genero, escola, perfil, turma);

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
        assertEquals(turma, aluno.getTurma());
    }

    @Test
    void naoDeveCriarAlunoComPrimeiroNomeVazio() throws Exception {

        String nomeUtilizador = "asaj01";
        int numeroAluno = 5;
        String sala = "1A";
        String corFavorita = "Azul";
        boolean possuiDaltonismo = true;

        String primeiroNome = "";
        String sobrenome = "Asa";
        Genero genero = Genero.FEMININO;
        Perfil perfil = Perfil.ALUNO;

        Escola escola = new Escola("School", "Rua francisco da silva", "9234234445", "school@gmail.com");

        Professor professor = new Professor("Bruna", "Asa", "993838844", Genero.FEMININO, "bruna@gmail.com", "@Bemvindo123", TipoProfissional.PROFESSOR,  Perfil.PROFESSOR, escola);

        Turma turma = new Turma("Flor", "4B", 2026, "3º", "Flores verdes", professor);

        assertThrows(IllegalArgumentException.class, () -> {
            new Aluno(primeiroNome, sobrenome, nomeUtilizador, numeroAluno, sala, corFavorita, possuiDaltonismo, genero, escola, perfil, turma);
        });
    }

    @Test
    void naoDeveCriarAlunoComSobrenomeVazio() throws Exception {

        String nomeUtilizador = "asaj01";
        int numeroAluno = 5;
        String sala = "1A";
        String corFavorita = "Azul";
        boolean possuiDaltonismo = true;

        String primeiroNome = "Bruna";
        String sobrenome = "";
        Genero genero = Genero.FEMININO;
        Perfil perfil = Perfil.ALUNO;

        Escola escola = new Escola("School", "Rua francisco da silva", "9234234445", "school@gmail.com");

        Professor professor = new Professor("Bruna", "Asa", "993838844", Genero.FEMININO, "bruna@gmail.com", "@Bemvindo123", TipoProfissional.PROFESSOR,  Perfil.PROFESSOR, escola);

        Turma turma = new Turma("Flor", "4B", 2026, "3º", "Flores verdes", professor);

        assertThrows(IllegalArgumentException.class, () -> {
            new Aluno(primeiroNome, sobrenome, nomeUtilizador, numeroAluno, sala, corFavorita, possuiDaltonismo, genero, escola, perfil, turma);
        });
    }

    @Test
    void naoDeveCriarAlunoComNomeUtilizadorVazio() throws Exception {

        String nomeUtilizador = "";
        int numeroAluno = 5;
        String sala = "1A";
        String corFavorita = "Azul";
        boolean possuiDaltonismo = true;

        String primeiroNome = "Bruna";
        String sobrenome = "Asa";
        Genero genero = Genero.FEMININO;
        Perfil perfil = Perfil.ALUNO;

        Escola escola = new Escola("School", "Rua francisco da silva", "9234234445", "school@gmail.com");

        Professor professor = new Professor("Bruna", "Asa", "993838844", Genero.FEMININO, "bruna@gmail.com", "@Bemvindo123", TipoProfissional.PROFESSOR,  Perfil.PROFESSOR, escola);

        Turma turma = new Turma("Flor", "4B", 2026, "3º", "Flores verdes", professor);

        assertThrows(IllegalArgumentException.class, () -> {
            new Aluno(primeiroNome, sobrenome, nomeUtilizador, numeroAluno, sala, corFavorita, possuiDaltonismo, genero, escola, perfil, turma);
        });

    }

    @Test
    void naoDeveCriarAlunoComCampoSalaVazio() throws Exception {

        String nomeUtilizador = "Bruna01";
        int numeroAluno = 5;
        String sala = "";
        String corFavorita = "Azul";
        boolean possuiDaltonismo = true;

        String primeiroNome = "Bruna";
        String sobrenome = "Asa";
        Genero genero = Genero.FEMININO;
        Perfil perfil = Perfil.ALUNO;

        Escola escola = new Escola("School", "Rua francisco da silva", "9234234445", "school@gmail.com");

        Professor professor = new Professor("Bruna", "Asa", "993838844", Genero.FEMININO, "bruna@gmail.com", "@Bemvindo123", TipoProfissional.PROFESSOR,  Perfil.PROFESSOR, escola);

        Turma turma = new Turma("Flor", "4B", 2026, "3º", "Flores verdes", professor);

        assertThrows(IllegalArgumentException.class, () -> {
            new Aluno(primeiroNome, sobrenome, nomeUtilizador, numeroAluno, sala, corFavorita, possuiDaltonismo, genero, escola, perfil, turma);
        });
    }

    @Test
    void naoDeveCriarAlunoComCampoCorFavoritaVazio() throws Exception {

        String nomeUtilizador = "Bruna01";
        int numeroAluno = 5;
        String sala = "1B";
        String corFavorita = "";
        boolean possuiDaltonismo = true;

        String primeiroNome = "Bruna";
        String sobrenome = "Asa";
        Genero genero = Genero.FEMININO;
        Perfil perfil = Perfil.ALUNO;

        Escola escola = new Escola("School", "Rua francisco da silva", "9234234445", "school@gmail.com");

        Professor professor = new Professor("Bruna", "Asa", "993838844", Genero.FEMININO, "bruna@gmail.com", "@Bemvindo123", TipoProfissional.PROFESSOR,  Perfil.PROFESSOR, escola);

        Turma turma = new Turma("Flor", "4B", 2026, "3º", "Flores verdes", professor);

        assertThrows(IllegalArgumentException.class, () -> {
            new Aluno(primeiroNome, sobrenome, nomeUtilizador, numeroAluno, sala, corFavorita, possuiDaltonismo, genero, escola, perfil, turma);
        });
    }
}
