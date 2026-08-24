package com.example.bespring.domainTests;

import com.example.bespring.domain.Escola;
import com.example.bespring.domain.Utilizador;
import com.example.bespring.domain.enums.Genero;
import com.example.bespring.domain.enums.Perfil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UtilizadorDomainTest {

    @Test
    void deveCriarUmUtilizadorComCamposValidos() {

        String primeiroNome = "Asa";
        String sobrenome = "Zuri";
        Genero genero = Genero.FEMININO;
        Perfil perfil = Perfil.ALUNO;

        Escola escola = new Escola("School", "Rua francisco da silva", "9234234445", "school@gmail.com");

        Utilizador utilizador = new Utilizador(primeiroNome, sobrenome, genero, perfil, escola);

        assertEquals(primeiroNome, utilizador.getPrimeiroNome());
        assertEquals(sobrenome, utilizador.getSobrenome());
        assertEquals(genero, utilizador.getGenero());
        assertEquals(perfil, utilizador.getPerfil());
        assertEquals(escola, utilizador.getEscola());

    }

    @Test
    void naoDeveCriarUmUtilizadorComPrimeiroNomeVazio() throws Exception {

        String primeiroNome = "";
        String sobrenome = "Zuri";
        Genero genero = Genero.FEMININO;
        Perfil perfil = Perfil.ALUNO;

        Escola escola = new Escola("School", "Rua francisco da silva", "9234234445", "school@gmail.com");

        assertThrows(IllegalArgumentException.class, () -> {
           new Utilizador(primeiroNome, sobrenome, genero, perfil, escola);
        });

    }

    @Test
    void naoDeveCriarUmUtilizadorComSobrenomeVazio() throws Exception {

        String primeiroNome = "Ana";
        String sobrenome = "";
        Genero genero = Genero.FEMININO;
        Perfil perfil = Perfil.ALUNO;

        Escola escola = new Escola("School", "Rua francisco da silva", "9234234445", "school@gmail.com");

        assertThrows(IllegalArgumentException.class, () -> {
           new Utilizador(primeiroNome, sobrenome, genero, perfil, escola);
        });

    }

}
