package com.example.bespring.modulosTest.utilizadoresTest.domainTests;

import com.example.bespring.modulosTest.utilizadoresTest.domain.*;
import com.example.bespring.modulosTest.utilizadoresTest.domain.enums.EstadoConta;
import com.example.bespring.modulosTest.utilizadoresTest.domain.enums.Genero;
import com.example.bespring.modulosTest.utilizadoresTest.domain.enums.Perfil;
import com.example.bespring.modulosTest.utilizadoresTest.domain.enums.TipoProfissional;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

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
        String senha = "123456";

        Utilizador admin = new Utilizador("Marta", "KingStone", Genero.FEMININO,
                LocalDate.of(1999,10,2), Perfil.ADMIN,"raquel@gmail.com", "maura12#",
                EstadoConta.ATIVA, null, null);
        admin.setIdUtilizador(1L);

        Utilizador adminEscola = new Utilizador("Raquel", "KingStone", Genero.FEMININO,
                LocalDate.of(2004,1,5), Perfil.ADMIN_ESCOLA,"raquel@gmail.com", "maura1234",
                EstadoConta.ATIVA, null, admin);
        adminEscola.setIdUtilizador(2L);

        Escola escola = new Escola("School", "Rua francisco da silva", "9234234445", "school@gmail.com");
        escola.setIdEscola(1L);
       // aluno.setEscola(escola);

        Professor professor = new Professor("92342A3444", "Bruna", "Asa", "993838844", Genero.FEMININO,
                LocalDate.of(1999,1,2), "bruna@gmail.com", "@Bemvindo123", TipoProfissional.PROFESSOR,
                Perfil.PROFESSOR, escola, EstadoConta.ATIVA, null, adminEscola);
        Turma turma = new Turma("Flor", "4B", 2026, "3º", "Flores verdes", professor);

        Aluno aluno = new Aluno(primeiroNome, sobrenome, nomeUtilizador, numeroAluno, sala, corFavorita, possuiDaltonismo, genero, escola, perfil, senha, turma, professor);

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
        assertEquals(senha, aluno.getSenha());
        assertEquals(turma, aluno.getTurma());
        assertEquals(professor, aluno.getProfessor());
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
        String senha = "123456";

        Utilizador admin = new Utilizador("Marta", "KingStone", Genero.FEMININO,
                LocalDate.of(1999,10,2), Perfil.ADMIN,"raquel@gmail.com", "maura12#",
                EstadoConta.ATIVA, null, null);
        admin.setIdUtilizador(1L);

        Utilizador adminEscola = new Utilizador("Raquel", "KingStone", Genero.FEMININO,
                LocalDate.of(2004,1,5), Perfil.ADMIN_ESCOLA,"raquel@gmail.com", "maura1234",
                EstadoConta.ATIVA, null, admin);
        adminEscola.setIdUtilizador(2L);

        Escola escola = new Escola("School", "Rua francisco da silva", "9234234445", "school@gmail.com");
        escola.setIdEscola(1L);
        //aluno.setEscola(escola);

        Professor professor = new Professor("92342A3444", "Bruna", "Asa", "993838844", Genero.FEMININO,
                LocalDate.of(1999,1,2), "bruna@gmail.com", "@Bemvindo123", TipoProfissional.PROFESSOR,
                Perfil.PROFESSOR, escola, EstadoConta.ATIVA, null, adminEscola);
        Turma turma = new Turma("Flor", "4B", 2026, "3º", "Flores verdes", professor);

        assertThrows(IllegalArgumentException.class, () -> {
            new Aluno(primeiroNome, sobrenome, nomeUtilizador, numeroAluno, sala, corFavorita, possuiDaltonismo, genero, escola, perfil, senha, turma, professor);
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
        String senha = "123456";

        Utilizador admin = new Utilizador("Marta", "KingStone", Genero.FEMININO,
                LocalDate.of(1999,10,2), Perfil.ADMIN,"raquel@gmail.com", "maura12#",
                EstadoConta.ATIVA, null, null);
        admin.setIdUtilizador(1L);

        Utilizador adminEscola = new Utilizador("Raquel", "KingStone", Genero.FEMININO,
                LocalDate.of(2004,1,5), Perfil.ADMIN_ESCOLA,"raquel@gmail.com", "maura1234",
                EstadoConta.ATIVA, null, admin);
        adminEscola.setIdUtilizador(2L);

        Escola escola = new Escola("School", "Rua francisco da silva", "9234234445", "school@gmail.com");
        escola.setIdEscola(1L);
        //aluno.setEscola(escola);

        Professor professor = new Professor("92342A3444", "Bruna", "Asa", "993838844", Genero.FEMININO,
                LocalDate.of(1999,1,2), "bruna@gmail.com", "@Bemvindo123", TipoProfissional.PROFESSOR,
                Perfil.PROFESSOR, escola, EstadoConta.ATIVA, null, adminEscola);
        Turma turma = new Turma("Flor", "4B", 2026, "3º", "Flores verdes", professor);

        assertThrows(IllegalArgumentException.class, () -> {
            new Aluno(primeiroNome, sobrenome, nomeUtilizador, numeroAluno, sala, corFavorita, possuiDaltonismo, genero, escola, perfil, senha, turma, professor);
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
        String senha = "123456";

        Utilizador admin = new Utilizador("Marta", "KingStone", Genero.FEMININO,
                LocalDate.of(1999,10,2), Perfil.ADMIN,"raquel@gmail.com", "maura12#",
                EstadoConta.ATIVA, null, null);
        admin.setIdUtilizador(1L);

        Utilizador adminEscola = new Utilizador("Raquel", "KingStone", Genero.FEMININO,
                LocalDate.of(2004,1,5), Perfil.ADMIN_ESCOLA,"raquel@gmail.com", "maura1234",
                EstadoConta.ATIVA, null, admin);
        adminEscola.setIdUtilizador(2L);

        Escola escola = new Escola("School", "Rua francisco da silva", "9234234445", "school@gmail.com");
        escola.setIdEscola(1L);
        //aluno.setEscola(escola);

        Professor professor = new Professor("92342A3444", "Bruna", "Asa", "993838844", Genero.FEMININO,
                LocalDate.of(1999,1,2), "bruna@gmail.com", "@Bemvindo123", TipoProfissional.PROFESSOR,
                Perfil.PROFESSOR, escola, EstadoConta.ATIVA, null, adminEscola);
        Turma turma = new Turma("Flor", "4B", 2026, "3º", "Flores verdes", professor);

        assertThrows(IllegalArgumentException.class, () -> {
            new Aluno(primeiroNome, sobrenome, nomeUtilizador, numeroAluno, sala, corFavorita, possuiDaltonismo, genero, escola, perfil, senha, turma, professor);
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
        String senha = "123456";

        Utilizador admin = new Utilizador("Marta", "KingStone", Genero.FEMININO,
                LocalDate.of(1999,10,2), Perfil.ADMIN,"raquel@gmail.com", "maura12#",
                EstadoConta.ATIVA, null, null);
        admin.setIdUtilizador(1L);

        Utilizador adminEscola = new Utilizador("Raquel", "KingStone", Genero.FEMININO,
                LocalDate.of(2004,1,5), Perfil.ADMIN_ESCOLA,"raquel@gmail.com", "maura1234",
                EstadoConta.ATIVA, null, admin);
        adminEscola.setIdUtilizador(2L);

        Escola escola = new Escola("School", "Rua francisco da silva", "9234234445", "school@gmail.com");
        escola.setIdEscola(1L);
        //aluno.setEscola(escola);

        Professor professor = new Professor("92342A3444", "Bruna", "Asa", "993838844", Genero.FEMININO,
                LocalDate.of(1999,1,2), "bruna@gmail.com", "@Bemvindo123", TipoProfissional.PROFESSOR,
                Perfil.PROFESSOR, escola, EstadoConta.ATIVA, null, adminEscola);
        Turma turma = new Turma("Flor", "4B", 2026, "3º", "Flores verdes", professor);

        assertThrows(IllegalArgumentException.class, () -> {
            new Aluno(primeiroNome, sobrenome, nomeUtilizador, numeroAluno, sala, corFavorita, possuiDaltonismo, genero, escola, perfil, senha, turma, professor);
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
        String senha = "123456";

        Utilizador admin = new Utilizador("Marta", "KingStone", Genero.FEMININO,
                LocalDate.of(1999,10,2), Perfil.ADMIN,"raquel@gmail.com", "maura12#",
                EstadoConta.ATIVA, null, null);
        admin.setIdUtilizador(1L);

        Utilizador adminEscola = new Utilizador("Raquel", "KingStone", Genero.FEMININO,
                LocalDate.of(2004,1,5), Perfil.ADMIN_ESCOLA,"raquel@gmail.com", "maura1234",
                EstadoConta.ATIVA, null, admin);
        adminEscola.setIdUtilizador(2L);

        Escola escola = new Escola("School", "Rua francisco da silva", "9234234445", "school@gmail.com");
        escola.setIdEscola(1L);
        //aluno.setEscola(escola);

        Professor professor = new Professor("92342A3444", "Bruna", "Asa", "993838844", Genero.FEMININO,
                LocalDate.of(1999,1,2), "bruna@gmail.com", "@Bemvindo123", TipoProfissional.PROFESSOR,
                Perfil.PROFESSOR, escola, EstadoConta.ATIVA, null, adminEscola);
        Turma turma = new Turma("Flor", "4B", 2026, "3º", "Flores verdes", professor);

        assertThrows(IllegalArgumentException.class, () -> {
            new Aluno(primeiroNome, sobrenome, nomeUtilizador, numeroAluno, sala, corFavorita, possuiDaltonismo, genero, escola, perfil, senha, turma, professor);
        });
    }
}
