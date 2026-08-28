package com.example.bespring.domainTests;

import com.example.bespring.domain.Aluno;
import com.example.bespring.domain.Escola;
import com.example.bespring.domain.UtilizadorProfissional;
import com.example.bespring.domain.enums.Genero;
import com.example.bespring.domain.enums.Perfil;
import com.example.bespring.domain.enums.TipoProfissional;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UtilizadorProfissionalDomainTest {

    @Test
    void deveCriarUtilizadorProfissionalComCamposValidos(){

        String primeiroNome = "Ana";
        String sobrenome = "Bell";
        String telefone = "933434445";
        Genero genero = Genero.FEMININO;
        String email = "ana@gmail.com";
        String senha = "Feliznatal2026%";
        TipoProfissional tipo = TipoProfissional.PROFESSOR;
        Perfil perfil = Perfil.ALUNO;

        Escola escola = new Escola("School", "Rua francisco da silva", "9234234445", "school@gmail.com");

        UtilizadorProfissional userProf = new UtilizadorProfissional(primeiroNome, sobrenome, telefone, genero, email, senha, tipo, perfil, escola);

        assertEquals(primeiroNome, userProf.getPrimeiroNome());
        assertEquals(sobrenome, userProf.getSobrenome());
        assertEquals(telefone, userProf.getTelefone());
        assertEquals(genero, userProf.getGenero());
        assertEquals(email, userProf.getEmail());
        assertEquals(senha, userProf.getSenha());
        assertEquals(tipo, userProf.getTipo());
        assertEquals(perfil, userProf.getPerfil());
        assertEquals(escola, userProf.getEscola());

    }

    @Test
    void naoDeveCriarUtilizadorProfissionalComCampoPrimeiroNomeVazio() throws Exception{

        String primeiroNome = "";
        String sobrenome = "Bell";
        String telefone = "933434445";
        Genero genero = Genero.FEMININO;
        String email = "ana@gmail.com";
        String senha = "123456";
        TipoProfissional tipo = TipoProfissional.PROFESSOR;
        Perfil perfil = Perfil.ALUNO;

        Escola escola = new Escola("School", "Rua francisco da silva", "9234234445", "school@gmail.com");

        assertThrows(IllegalArgumentException.class, () -> {
            new UtilizadorProfissional(primeiroNome, sobrenome, telefone, genero, email, senha, tipo, perfil, escola);
        });

    }

    @Test
    void naoDeveCriarUtilizadorProfissionalComCampoSobrenomeVazio() throws Exception{
        String primeiroNome = "Ana";
        String sobrenome = "";
        String telefone = "933434445";
        Genero genero = Genero.FEMININO;
        String email = "ana@gmail.com";
        String senha = "123456";
        TipoProfissional tipo = TipoProfissional.PROFESSOR;
        Perfil perfil = Perfil.ALUNO;

        Escola escola = new Escola("School", "Rua francisco da silva", "9234234445", "school@gmail.com");

        assertThrows(IllegalArgumentException.class, () -> {
            new UtilizadorProfissional(primeiroNome, sobrenome, telefone, genero, email, senha, tipo, perfil, escola);
        });
    }

    @Test
    void naoDeveCriarUtilizadorProfissionalComCampoTelefoneVazio() throws Exception{
        String primeiroNome = "Ana";
        String sobrenome = "Bell";
        String telefone = "";
        Genero genero = Genero.FEMININO;
        String email = "ana@gmail.com";
        String senha = "123456";
        TipoProfissional tipo = TipoProfissional.PROFESSOR;
        Perfil perfil = Perfil.ALUNO;

        Escola escola = new Escola("School", "Rua francisco da silva", "9234234445", "school@gmail.com");

        assertThrows(IllegalArgumentException.class, () -> {
            new UtilizadorProfissional(primeiroNome, sobrenome, telefone, genero, email, senha, tipo, perfil, escola);
        });
    }

    @Test
    void naoDeveCriarUtilizadorProfissionalComCampoEmailVazio() throws Exception{

        String primeiroNome = "Ana";
        String sobrenome = "Bell";
        String telefone = "933434445";
        Genero genero = Genero.FEMININO;
        String email = "";
        String senha = "123456";
        TipoProfissional tipo = TipoProfissional.PROFESSOR;
        Perfil perfil = Perfil.ALUNO;

        Escola escola = new Escola("School", "Rua francisco da silva", "9234234445", "school@gmail.com");

        assertThrows(IllegalArgumentException.class, () -> {
            new UtilizadorProfissional(primeiroNome, sobrenome, telefone, genero, email, senha, tipo, perfil, escola);
        });
    }

    @Test
    void naoDeveCriarUtilizadorProfissionalComCampoSenhaVazio() throws Exception{
        String primeiroNome = "Ana";
        String sobrenome = "Bell";
        String telefone = "933434445";
        Genero genero = Genero.FEMININO;
        String email = "ana@gmail.com";
        String senha = "";
        TipoProfissional tipo = TipoProfissional.PROFESSOR;
        Perfil perfil = Perfil.ALUNO;

        Escola escola = new Escola("School", "Rua francisco da silva", "9234234445", "school@gmail.com");

        assertThrows(IllegalArgumentException.class, () -> {
            new UtilizadorProfissional(primeiroNome, sobrenome, telefone, genero, email, senha, tipo, perfil, escola);
        });
    }

    @Test
    void naoDeveCriarUtilizadorProfissionalComSenhaCurta() throws Exception{

        String primeiroNome = "Ana";
        String sobrenome = "Bell";
        String telefone = "933434445";
        Genero genero = Genero.FEMININO;
        String email = "ana@gmail.com";
        String senha = "asdf354";
        TipoProfissional tipo = TipoProfissional.PROFESSOR;
        Perfil perfil = Perfil.ALUNO;

        Escola escola = new Escola("School", "Rua francisco da silva", "9234234445", "school@gmail.com");

        assertThrows(IllegalArgumentException.class, () -> {
            new UtilizadorProfissional(primeiroNome, sobrenome, telefone, genero, email, senha, tipo, perfil, escola);
        });
    }

    @Test
    void naoDeveCriarUtilizadorProfissonalComSenhaMuitoLonga() throws Exception{

        String primeiroNome = "Ana";
        String sobrenome = "Bell";
        String telefone = "933434445";
        Genero genero = Genero.FEMININO;
        String email = "ana@gmail.com";
        String senha = "asdf3523kkkdjjdfsdfghfghfvfgdff";
        TipoProfissional tipo = TipoProfissional.PROFESSOR;
        Perfil perfil = Perfil.ALUNO;

        Escola escola = new Escola("School", "Rua francisco da silva", "9234234445", "school@gmail.com");

        assertThrows(IllegalArgumentException.class, () -> {
            new UtilizadorProfissional(primeiroNome, sobrenome, telefone, genero, email, senha, tipo, perfil, escola);
        });

    }

    @Test
    void naoDeveCriarUtilizadorProfissionalComSenhaSemLetraMaiuscula() throws Exception{

        String primeiroNome = "Ana";
        String sobrenome = "Bell";
        String telefone = "933434445";
        Genero genero = Genero.FEMININO;
        String email = "ana@gmail.com";
        String senha = "avante";
        TipoProfissional tipo = TipoProfissional.PROFESSOR;
        Perfil perfil = Perfil.ALUNO;

        Escola escola = new Escola("School", "Rua francisco da silva", "9234234445", "school@gmail.com");

        assertThrows(IllegalArgumentException.class, () -> {
            new UtilizadorProfissional(primeiroNome, sobrenome, telefone, genero, email, senha, tipo, perfil, escola);
        });
    }

    @Test
    void naoDeveCriarUtilizadorProfissionalComSenhaSemLetraMinuscula() throws Exception{
        String primeiroNome = "Ana";
        String sobrenome = "Bell";
        String telefone = "933434445";
        Genero genero = Genero.FEMININO;
        String email = "ana@gmail.com";
        String senha = "AVANTE";
        TipoProfissional tipo = TipoProfissional.PROFESSOR;
        Perfil perfil = Perfil.ALUNO;

        Escola escola = new Escola("School", "Rua francisco da silva", "9234234445", "school@gmail.com");

        assertThrows(IllegalArgumentException.class, () -> {
            new UtilizadorProfissional(primeiroNome, sobrenome, telefone, genero, email, senha, tipo, perfil, escola);
        });
    }

    @Test
    void naoDeveCriarUtilizadorProfissionaComSenhaSemNumero() throws Exception{
        String primeiroNome = "Ana";
        String sobrenome = "Bell";
        String telefone = "933434445";
        Genero genero = Genero.FEMININO;
        String email = "ana@gmail.com";
        String senha = "Avante";
        TipoProfissional tipo = TipoProfissional.PROFESSOR;
        Perfil perfil = Perfil.ALUNO;

        Escola escola = new Escola("School", "Rua francisco da silva", "9234234445", "school@gmail.com");

        assertThrows(IllegalArgumentException.class, () -> {
            new UtilizadorProfissional(primeiroNome, sobrenome, telefone, genero, email, senha, tipo, perfil, escola);
        });
    }

    @Test
    void naoDeveCriarUtilizadorPrissionalComSenhaSemCaracteresEspeciais() throws Exception{
        String primeiroNome = "Ana";
        String sobrenome = "Bell";
        String telefone = "933434445";
        Genero genero = Genero.FEMININO;
        String email = "ana@gmail.com";
        String senha = "@Avante";
        TipoProfissional tipo = TipoProfissional.PROFESSOR;
        Perfil perfil = Perfil.ALUNO;

        Escola escola = new Escola("School", "Rua francisco da silva", "9234234445", "school@gmail.com");

        assertThrows(IllegalArgumentException.class, () -> {
            new UtilizadorProfissional(primeiroNome, sobrenome, telefone, genero, email, senha, tipo, perfil, escola);
        });

    }
}
