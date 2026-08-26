package com.example.bespring.domainTests;

import com.example.bespring.domain.Turma;
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

        Turma turma = new Turma(nome, sala, anoLetivo, anoSerie, descricao);

        assertEquals(nome, turma.getNome());
        assertEquals(sala, turma.getSala());
        assertEquals(anoLetivo, turma.getAnoLetivo());
        assertEquals(anoSerie, turma.getAnoSerie());
        assertEquals(descricao, turma.getDescricao());
    }

    @Test
    void naoDeveCriarTurmaComCampoNomeVazio() throws Exception{

        String nome = "";
        String sala = "2B";
        int anoLetivo = 2026;
        String anoSerie = "3º";
        String descricao = "É uma turma que vive no fundo do mar";

        assertThrows(IllegalArgumentException.class, () -> {
            new Turma(nome, sala, anoLetivo, anoSerie, descricao);
        });
    }

    @Test
    void naoDeveCriarTurmaComCampoSalaVazio() throws Exception{

        String nome = "Aqua";
        String sala = "";
        int anoLetivo = 2026;
        String anoSerie = "3º";
        String descricao = "É uma turma que vive no fundo do mar";

        assertThrows(IllegalArgumentException.class, () -> {
            new Turma(nome, sala, anoLetivo, anoSerie, descricao);
        });
    }

    @Test
    void naoDeveCriarTurmaComCampoAnoLetivoInvalido() throws Exception{
        String nome = "Aqua";
        String sala = "4B";
        int anoLetivo = 2300;
        String anoSerie = "3º";
        String descricao = "É uma turma que vive no fundo do mar";

        assertThrows(IllegalArgumentException.class, () -> {
            new Turma(nome, sala, anoLetivo, anoSerie, descricao);
        });
    }

    @Test
    void naoDeveCriarTurmaComCampoAnoSerieVazio() throws Exception{
        String nome = "Aqua";
        String sala = "2B";
        int anoLetivo = 2026;
        String anoSerie = "";
        String descricao = "É uma turma que vive no fundo do mar";

        assertThrows(IllegalArgumentException.class, () -> {
            new Turma(nome, sala, anoLetivo, anoSerie, descricao);
        });
    }

    @Test
    void naoDeveCriarTurmaComCampoDescricaoVazio() throws Exception{
        String nome = "Aqua";
        String sala = "2B";
        int anoLetivo = 2026;
        String anoSerie = "3º";
        String descricao = "";

        assertThrows(IllegalArgumentException.class, () -> {
            new Turma(nome, sala, anoLetivo, anoSerie, descricao);
        });
    }
}
