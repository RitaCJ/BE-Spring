package com.example.bespring.modulosTest.utilizadoresTest.domainTests;

import com.example.bespring.modulosTest.utilizadoresTest.domain.Escola;
import com.example.bespring.modulosTest.utilizadoresTest.domain.Professor;
import com.example.bespring.modulosTest.utilizadoresTest.domain.Turma;
import com.example.bespring.modulosTest.utilizadoresTest.domain.Utilizador;
import com.example.bespring.modulosTest.utilizadoresTest.domain.enums.EstadoConta;
import com.example.bespring.modulosTest.utilizadoresTest.domain.enums.Genero;
import com.example.bespring.modulosTest.utilizadoresTest.domain.enums.Perfil;
import com.example.bespring.modulosTest.utilizadoresTest.domain.enums.TipoProfissional;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TurmaDomainTest {

    @Test
    void deveCriarTurmaComCamposValidos(){

        String nome = "Aqua";
        String sala = "2B";
        int anoLetivo = 2025;
        String anoSerie = "3º";
        String descricao = "É uma turma que vive no fundo do mar";

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
        Turma turma = new Turma(nome, sala, anoLetivo, anoSerie, descricao, professor);

        assertEquals(nome, turma.getNome());
        assertEquals(sala, turma.getSala());
        assertEquals(anoLetivo, turma.getAnoLetivo());
        assertEquals(anoSerie, turma.getAnoSerie());
        assertEquals(descricao, turma.getDescricao());
        assertEquals(professor, turma.getProfessor());
    }

    @Test
    void naoDeveCriarTurmaComCampoNomeVazio() throws Exception{

        String nome = "";
        String sala = "2B";
        int anoLetivo = 2026;
        String anoSerie = "3º";
        String descricao = "É uma turma que vive no fundo do mar";

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

        assertThrows(IllegalArgumentException.class, () -> {
            new Turma(nome, sala, anoLetivo, anoSerie, descricao, professor);
        });
    }

    @Test
    void naoDeveCriarTurmaComCampoSalaVazio() throws Exception{

        String nome = "Aqua";
        String sala = "";
        int anoLetivo = 2026;
        String anoSerie = "3º";
        String descricao = "É uma turma que vive no fundo do mar";

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

        assertThrows(IllegalArgumentException.class, () -> {
            new Turma(nome, sala, anoLetivo, anoSerie, descricao, professor);
        });
    }

    @Test
    void naoDeveCriarTurmaComCampoAnoLetivoInvalido() throws Exception{
        String nome = "Aqua";
        String sala = "4B";
        int anoLetivo = 2300;
        String anoSerie = "3º";
        String descricao = "É uma turma que vive no fundo do mar";

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
        assertThrows(IllegalArgumentException.class, () -> {
            new Turma(nome, sala, anoLetivo, anoSerie, descricao, professor);
        });
    }

    @Test
    void naoDeveCriarTurmaComCampoAnoSerieVazio() throws Exception{
        String nome = "Aqua";
        String sala = "2B";
        int anoLetivo = 2026;
        String anoSerie = "";
        String descricao = "É uma turma que vive no fundo do mar";

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

        assertThrows(IllegalArgumentException.class, () -> {
            new Turma(nome, sala, anoLetivo, anoSerie, descricao, professor);
        });
    }

    @Test
    void naoDeveCriarTurmaComCampoDescricaoVazio() throws Exception{
        String nome = "Aqua";
        String sala = "2B";
        int anoLetivo = 2026;
        String anoSerie = "3º";
        String descricao = "";

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
        assertThrows(IllegalArgumentException.class, () -> {
            new Turma(nome, sala, anoLetivo, anoSerie, descricao, professor);
        });
    }
}
