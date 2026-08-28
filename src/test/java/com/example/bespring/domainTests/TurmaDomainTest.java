package com.example.bespring.domainTests;

import com.example.bespring.domain.Escola;
import com.example.bespring.domain.Professor;
import com.example.bespring.domain.Turma;
import com.example.bespring.domain.enums.Genero;
import com.example.bespring.domain.enums.Perfil;
import com.example.bespring.domain.enums.TipoProfissional;
import org.junit.jupiter.api.Test;

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

        Escola escola = new Escola("School", "Rua francisco da silva", "9234234445", "school@gmail.com");

        Professor professor = new Professor("Bruna", "Asa", "993838844", Genero.FEMININO, "bruna@gmail.com", "@Bemvindo123", TipoProfissional.PROFESSOR,  Perfil.PROFESSOR, escola);

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

        Escola escola = new Escola("School", "Rua francisco da silva", "9234234445", "school@gmail.com");

        Professor professor = new Professor("Bruna", "Asa", "993838844", Genero.FEMININO, "bruna@gmail.com", "@Bemvindo123", TipoProfissional.PROFESSOR,  Perfil.PROFESSOR, escola);


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

        Escola escola = new Escola("School", "Rua francisco da silva", "9234234445", "school@gmail.com");

        Professor professor = new Professor("Bruna", "Asa", "993838844", Genero.FEMININO, "bruna@gmail.com", "@Bemvindo123", TipoProfissional.PROFESSOR,  Perfil.PROFESSOR, escola);


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

        Escola escola = new Escola("School", "Rua francisco da silva", "9234234445", "school@gmail.com");

        Professor professor = new Professor("Bruna", "Asa", "993838844", Genero.FEMININO, "bruna@gmail.com", "@Bemvindo123", TipoProfissional.PROFESSOR,  Perfil.PROFESSOR, escola);

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

        Escola escola = new Escola("School", "Rua francisco da silva", "9234234445", "school@gmail.com");

        Professor professor = new Professor("Bruna", "Asa", "993838844", Genero.FEMININO, "bruna@gmail.com", "@Bemvindo123", TipoProfissional.PROFESSOR,  Perfil.PROFESSOR, escola);


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

        Escola escola = new Escola("School", "Rua francisco da silva", "9234234445", "school@gmail.com");

        Professor professor = new Professor("Bruna", "Asa", "993838844", Genero.FEMININO, "bruna@gmail.com", "@Bemvindo123", TipoProfissional.PROFESSOR,  Perfil.PROFESSOR, escola);

        assertThrows(IllegalArgumentException.class, () -> {
            new Turma(nome, sala, anoLetivo, anoSerie, descricao, professor);
        });
    }
}
