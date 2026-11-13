package com.example.bespring.modulosTest.utilizadoresTest.domainTests;

import com.example.bespring.modulosTest.utilizadoresTest.domain.Escola;
import com.example.bespring.modulosTest.utilizadoresTest.domain.Utilizador;
import com.example.bespring.modulosTest.utilizadoresTest.domain.enums.EstadoConta;
import com.example.bespring.modulosTest.utilizadoresTest.domain.enums.Genero;
import com.example.bespring.modulosTest.utilizadoresTest.domain.enums.Perfil;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UtilizadorDomainTest {

    @Test
    void deveCriarUmUtilizadorComCamposValidos() {

        String primeiroNome = "Asa";
        String sobrenome = "Zuri";
        Genero genero = Genero.FEMININO;
        LocalDate dataNascimento = LocalDate.of(1999, 2, 1);
        Perfil perfil = Perfil.PROFESSOR;
        String login = "ana@gmail.com";
        String senha = "Feliznatal2026%";
        EstadoConta estadoConta = EstadoConta.PENDENTE;

        Escola escola = new Escola("School", "Rua francisco da silva", "923423444", "school@gmail.com");

        Utilizador utilizador = new Utilizador(primeiroNome, sobrenome, genero, dataNascimento, perfil, login, senha, estadoConta, escola);

        assertEquals(primeiroNome, utilizador.getPrimeiroNome());
        assertEquals(sobrenome, utilizador.getSobrenome());
        assertEquals(genero, utilizador.getGenero());
        assertEquals(dataNascimento, utilizador.getDataNascimento());
        assertEquals(perfil, utilizador.getPerfil());
        assertEquals(login, utilizador.getLogin());
        assertEquals(senha, utilizador.getSenha());
        assertEquals(estadoConta, utilizador.getEstadoConta());
        assertEquals(escola, utilizador.getEscola());

    }

    @Test
    void naoDeveCriarUmUtilizadorComPrimeiroNomeVazio() throws Exception {

        String primeiroNome = "";
        String sobrenome = "Zuri";
        Genero genero = Genero.FEMININO;
        LocalDate dataNascimento = LocalDate.of(1999, 2, 1);
        Perfil perfil = Perfil.PROFESSOR;
        String login = "ana@gmail.com";
        String senha = "Feliznatal2026%";
        EstadoConta estadoConta = EstadoConta.PENDENTE;

        Escola escola = new Escola("School", "Rua francisco da silva", "923423444", "school@gmail.com");

        assertThrows(IllegalArgumentException.class, () -> {
           new Utilizador(primeiroNome, sobrenome, genero, dataNascimento, perfil, login, senha, estadoConta, escola);
        });

    }

    @Test
    void naoDeveCriarUmUtilizadorComSobrenomeVazio() throws Exception {

        String primeiroNome = "Ana";
        String sobrenome = "";
        Genero genero = Genero.FEMININO;
        LocalDate dataNascimento = LocalDate.of(1999, 2, 1);
        Perfil perfil = Perfil.PROFESSOR;
        String login = "ana@gmail.com";
        String senha = "Feliznatal2026%";
        EstadoConta estadoConta = EstadoConta.PENDENTE;

        Escola escola = new Escola("School", "Rua francisco da silva", "923423444", "school@gmail.com");

        assertThrows(IllegalArgumentException.class, () -> {
           new Utilizador(primeiroNome, sobrenome, genero, dataNascimento, perfil, login, senha, estadoConta, escola);
        });

    }

    @Test
    void naoDeveCriarUmUtilizadorComLoginVazio() throws Exception {

        String primeiroNome = "Ana";
        String sobrenome = "Zuri";
        Genero genero = Genero.FEMININO;
        LocalDate dataNascimento = LocalDate.of(1999, 2, 1);
        Perfil perfil = Perfil.PROFESSOR;
        String login = "";
        String senha = "Feliznatal2026%";
        EstadoConta estadoConta = EstadoConta.PENDENTE;

        Escola escola = new Escola("School", "Rua francisco da silva", "923423444", "school@gmail.com");

        assertThrows(IllegalArgumentException.class, () -> {
            new Utilizador(primeiroNome, sobrenome, genero, dataNascimento, perfil, login, senha, estadoConta, escola);
        });

    }

    @Test
    void naoDeveCriarUmUtilizadorComSenhaVazia() throws Exception {

        String primeiroNome = "Ana";
        String sobrenome = "Zuri";
        Genero genero = Genero.FEMININO;
        LocalDate dataNascimento = LocalDate.of(2018, 5, 10);
        Perfil perfil = Perfil.ALUNO;
        String login = "Lua0121";
        String senha = "";
        EstadoConta estadoConta = EstadoConta.PENDENTE;

        Escola escola = new Escola("School", "Rua francisco da silva", "923423444", "school@gmail.com");

        assertThrows(IllegalArgumentException.class, () -> {
            new Utilizador(primeiroNome, sobrenome, genero, dataNascimento, perfil, login, senha, estadoConta, escola);
        });
    }

    @Test
    void naoDeveAceitarDataNascimentoNula() throws Exception {

        String primeiroNome = "Ana";
        String sobrenome = "Zuri";
        Genero genero = Genero.FEMININO;
        LocalDate dataNascimento = null;
        Perfil perfil = Perfil.ALUNO;
        String login = "Lua0121";
        String senha = "Feliznatal2026%";
        EstadoConta estadoConta = EstadoConta.PENDENTE;

        Escola escola = new Escola("School", "Rua francisco da silva", "923423444", "school@gmail.com");

        assertThrows(IllegalArgumentException.class, () -> {
            new Utilizador(primeiroNome, sobrenome, genero, dataNascimento, perfil, login, senha, estadoConta, escola);
        });
    }

    @Test
    void naoDeveAceitarDataNascimentoFutura() throws Exception {
        String primeiroNome = "Ana";
        String sobrenome = "Zuri";
        Genero genero = Genero.FEMININO;
        LocalDate dataNascimento = LocalDate.now().plusDays(1);
        Perfil perfil = Perfil.ALUNO;
        String login = "Lua0121";
        String senha = "Feliznatal2026%";
        EstadoConta estadoConta = EstadoConta.PENDENTE;

        Escola escola = new Escola("School", "Rua francisco da silva", "923423444", "school@gmail.com");

        assertThrows(IllegalArgumentException.class, () -> {
            new Utilizador(primeiroNome, sobrenome, genero, dataNascimento, perfil, login, senha, estadoConta, escola);
        });
    }
}
