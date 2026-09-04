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
        String login = "ana@gmail.com";
        String senha = "Feliznatal2026%";

        Escola escola = new Escola("School", "Rua francisco da silva", "9234234445", "school@gmail.com");

        Utilizador utilizador = new Utilizador(primeiroNome, sobrenome, genero, perfil, login, senha, escola);

        assertEquals(primeiroNome, utilizador.getPrimeiroNome());
        assertEquals(sobrenome, utilizador.getSobrenome());
        assertEquals(genero, utilizador.getGenero());
        assertEquals(perfil, utilizador.getPerfil());
        assertEquals(login, utilizador.getLogin());
        assertEquals(senha, utilizador.getSenha());
        assertEquals(escola, utilizador.getEscola());

    }

    @Test
    void naoDeveCriarUmUtilizadorComPrimeiroNomeVazio() throws Exception {

        String primeiroNome = "";
        String sobrenome = "Zuri";
        Genero genero = Genero.FEMININO;
        Perfil perfil = Perfil.ALUNO;
        String login = "ana@gmail.com";
        String senha = "Feliznatal2026%";

        Escola escola = new Escola("School", "Rua francisco da silva", "9234234445", "school@gmail.com");

        assertThrows(IllegalArgumentException.class, () -> {
           new Utilizador(primeiroNome, sobrenome, genero, perfil, login, senha, escola);
        });

    }

    @Test
    void naoDeveCriarUmUtilizadorComSobrenomeVazio() throws Exception {

        String primeiroNome = "Ana";
        String sobrenome = "";
        Genero genero = Genero.FEMININO;
        Perfil perfil = Perfil.ALUNO;
        String login = "Lua0121";
        String senha = "Feliznatal2026%";

        Escola escola = new Escola("School", "Rua francisco da silva", "9234234445", "school@gmail.com");

        assertThrows(IllegalArgumentException.class, () -> {
           new Utilizador(primeiroNome, sobrenome, genero, perfil, login, senha, escola);
        });

    }

    @Test
    void naoDeveCriarUmUtilizadorComLoginVazio() throws Exception {

        String primeiroNome = "Ana";
        String sobrenome = "";
        Genero genero = Genero.FEMININO;
        Perfil perfil = Perfil.ALUNO;
        String login = "";
        String senha = "Feliznatal2026%";

        Escola escola = new Escola("School", "Rua francisco da silva", "9234234445", "school@gmail.com");

        assertThrows(IllegalArgumentException.class, () -> {
            new Utilizador(primeiroNome, sobrenome, genero, perfil, login, senha, escola);
        });

    }

    @Test
    void naoDeveCriarUmUtilizadorComSenhaVazia() throws Exception {

        String primeiroNome = "Ana";
        String sobrenome = "";
        Genero genero = Genero.FEMININO;
        Perfil perfil = Perfil.ALUNO;
        String login = "Lua0121";
        String senha = "";

        Escola escola = new Escola("School", "Rua francisco da silva", "9234234445", "school@gmail.com");

        assertThrows(IllegalArgumentException.class, () -> {
            new Utilizador(primeiroNome, sobrenome, genero, perfil, login, senha, escola);
        });

    }

}
