package com.example.bespring.modulosTest.utilizadoresTest.domainTests;

import com.example.bespring.modulosTest.utilizadoresTest.domain.Escola;
import com.example.bespring.modulosTest.utilizadoresTest.domain.Utilizador;
import com.example.bespring.modulosTest.utilizadoresTest.domain.UtilizadorProfissional;
import com.example.bespring.modulosTest.utilizadoresTest.domain.enums.EstadoConta;
import com.example.bespring.modulosTest.utilizadoresTest.domain.enums.Genero;
import com.example.bespring.modulosTest.utilizadoresTest.domain.enums.Perfil;
import com.example.bespring.modulosTest.utilizadoresTest.domain.enums.TipoProfissional;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class UtilizadorProfissionalDomainTest {

    @Test
    void deveCriarUtilizadorProfissionalComCamposValidos(){
        String numeroIdentificacao = "123456789";
        String primeiroNome = "Ana";
        String sobrenome = "Bell";
        String telefone = "933434445";
        Genero genero = Genero.FEMININO;
        LocalDate dataNascimento = LocalDate.of(1990, 1, 1);
        String email = "ana@gmail.com";
        String senha = "Feliznatal2026%";
        EstadoConta estadoConta = EstadoConta.PENDENTE;
        Utilizador criadoPor = null;
        Utilizador aprovadoPor = null;

        TipoProfissional tipo = TipoProfissional.PROFESSOR;
        Perfil perfil = Perfil.PROFESSOR;

        Escola escola = new Escola("School", "Rua francisco da silva", "923423445", "school@gmail.com");

        UtilizadorProfissional userProf = new UtilizadorProfissional(numeroIdentificacao, primeiroNome, sobrenome, telefone, genero, dataNascimento, email,
                senha, tipo, perfil, escola, estadoConta, criadoPor, aprovadoPor);

        assertEquals(numeroIdentificacao, userProf.getNumeroIdentificacao());
        assertEquals(primeiroNome, userProf.getPrimeiroNome());
        assertEquals(sobrenome, userProf.getSobrenome());
        assertEquals(telefone, userProf.getTelefone());
        assertEquals(genero, userProf.getGenero());
        assertEquals(dataNascimento, userProf.getDataNascimento());
        assertEquals(email, userProf.getEmail());
        assertEquals(senha, userProf.getSenha());
        assertEquals(tipo, userProf.getTipo());
        assertEquals(perfil, userProf.getPerfil());
        assertEquals(escola, userProf.getEscola());
        assertEquals(estadoConta, userProf.getEstadoConta());
        assertEquals(criadoPor, userProf.getCriadoPor());
        assertEquals(aprovadoPor, userProf.getAprovadoPor());

    }

    @Test
    void naoDeveCriarUtilizadorProfissionalComCampoPrimeiroNomeVazio() throws Exception{
        String numeroIdentificacao = "123456789";
        String primeiroNome = "";
        String sobrenome = "Bell";
        String telefone = "933434445";
        Genero genero = Genero.FEMININO;
        LocalDate dataNascimento = LocalDate.of(1990, 1, 1);
        String email = "ana@gmail.com";
        String senha = "Feliznatal2026%";
        EstadoConta estadoConta = EstadoConta.PENDENTE;
        Utilizador criadoPor = null;
        Utilizador aprovadoPor = null;

        TipoProfissional tipo = TipoProfissional.PROFESSOR;
        Perfil perfil = Perfil.PROFESSOR;

        Escola escola = new Escola("School", "Rua francisco da silva", "923423445", "school@gmail.com");

        assertThrows(IllegalArgumentException.class, () -> {
            new UtilizadorProfissional(numeroIdentificacao, primeiroNome, sobrenome, telefone, genero, dataNascimento, email,
                    senha, tipo, perfil, escola, estadoConta, criadoPor, aprovadoPor);
        });

    }

    @Test
    void naoDeveCriarUtilizadorProfissionalComCampoSobrenomeVazio() throws Exception{
        String numeroIdentificacao = "123456789";
        String primeiroNome = "Ana";
        String sobrenome = "";
        String telefone = "933434445";
        Genero genero = Genero.FEMININO;
        LocalDate dataNascimento = LocalDate.of(1990, 1, 1);
        String email = "ana@gmail.com";
        String senha = "Feliznatal2026%";
        EstadoConta estadoConta = EstadoConta.PENDENTE;
        Utilizador criadoPor = null;
        Utilizador aprovadoPor = null;

        TipoProfissional tipo = TipoProfissional.PROFESSOR;
        Perfil perfil = Perfil.PROFESSOR;

        Escola escola = new Escola("School", "Rua francisco da silva", "923423445", "school@gmail.com");

        assertThrows(IllegalArgumentException.class, () -> {
            new UtilizadorProfissional(numeroIdentificacao, primeiroNome, sobrenome, telefone, genero, dataNascimento, email,
                    senha, tipo, perfil, escola, estadoConta, criadoPor, aprovadoPor);
        });
    }

    @Test
    void naoDeveCriarUtilizadorProfissionalComCampoTelefoneVazio() throws Exception{
        String numeroIdentificacao = "123456789";
        String primeiroNome = "Ana";
        String sobrenome = "Bell";
        String telefone = "";
        Genero genero = Genero.FEMININO;
        LocalDate dataNascimento = LocalDate.of(1990, 1, 1);
        String email = "ana@gmail.com";
        String senha = "Feliznatal2026%";
        EstadoConta estadoConta = EstadoConta.PENDENTE;
        Utilizador criadoPor = null;
        Utilizador aprovadoPor = null;

        TipoProfissional tipo = TipoProfissional.PROFESSOR;
        Perfil perfil = Perfil.PROFESSOR;

        Escola escola = new Escola("School", "Rua francisco da silva", "923423445", "school@gmail.com");

        assertThrows(IllegalArgumentException.class, () -> {
            new UtilizadorProfissional(numeroIdentificacao, primeiroNome, sobrenome, telefone, genero, dataNascimento, email,
                    senha, tipo, perfil, escola, estadoConta, criadoPor, aprovadoPor);
        });
    }

    @Test
    void naoDeveCriarUtilizadorProfissionalComCampoEmailVazio() throws Exception{
        String numeroIdentificacao = "123456789";
        String primeiroNome = "Ana";
        String sobrenome = "Bell";
        String telefone = "933434445";
        Genero genero = Genero.FEMININO;
        LocalDate dataNascimento = LocalDate.of(1990, 1, 1);
        String email = "";
        String senha = "Feliznatal2026%";
        EstadoConta estadoConta = EstadoConta.PENDENTE;
        Utilizador criadoPor = null;
        Utilizador aprovadoPor = null;

        TipoProfissional tipo = TipoProfissional.PROFESSOR;
        Perfil perfil = Perfil.PROFESSOR;

        Escola escola = new Escola("School", "Rua francisco da silva", "923423445", "school@gmail.com");

        assertThrows(IllegalArgumentException.class, () -> {
            new UtilizadorProfissional(numeroIdentificacao, primeiroNome, sobrenome, telefone, genero, dataNascimento, email,
                    senha, tipo, perfil, escola, estadoConta, criadoPor, aprovadoPor);
        });

    }

    @Test
    void naoDeveCriarUtilizadorProfissionalComCampoSenhaVazio() throws Exception{
        String numeroIdentificacao = "123456789";
        String primeiroNome = "Ana";
        String sobrenome = "Bell";
        String telefone = "933434445";
        Genero genero = Genero.FEMININO;
        LocalDate dataNascimento = LocalDate.of(1990, 1, 1);
        String email = "ana@gmail.com";
        String senha = "";
        EstadoConta estadoConta = EstadoConta.PENDENTE;
        Utilizador criadoPor = null;
        Utilizador aprovadoPor = null;

        TipoProfissional tipo = TipoProfissional.PROFESSOR;
        Perfil perfil = Perfil.PROFESSOR;

        Escola escola = new Escola("School", "Rua francisco da silva", "923423445", "school@gmail.com");

        assertThrows(IllegalArgumentException.class, () -> {
            new UtilizadorProfissional(numeroIdentificacao, primeiroNome, sobrenome, telefone, genero, dataNascimento, email,
                    senha, tipo, perfil, escola, estadoConta, criadoPor, aprovadoPor);
        });
    }

    @Test
    void naoDeveCriarUtilizadorProfissionalComSenhaCurta() throws Exception{
        String numeroIdentificacao = "123456789";
        String primeiroNome = "Ana";
        String sobrenome = "Bell";
        String telefone = "933434445";
        Genero genero = Genero.FEMININO;
        LocalDate dataNascimento = LocalDate.of(1990, 1, 1);
        String email = "ana@gmail.com";
        String senha = "asdf354";
        EstadoConta estadoConta = EstadoConta.PENDENTE;
        Utilizador criadoPor = null;
        Utilizador aprovadoPor = null;

        TipoProfissional tipo = TipoProfissional.PROFESSOR;
        Perfil perfil = Perfil.PROFESSOR;

        Escola escola = new Escola("School", "Rua francisco da silva", "923423445", "school@gmail.com");

        assertThrows(IllegalArgumentException.class, () -> {
            new UtilizadorProfissional(numeroIdentificacao, primeiroNome, sobrenome, telefone, genero, dataNascimento, email,
                    senha, tipo, perfil, escola, estadoConta, criadoPor, aprovadoPor);
        });
    }

    @Test
    void naoDeveCriarUtilizadorProfissonalComSenhaMuitoLonga() throws Exception{

        String numeroIdentificacao = "123456789";
        String primeiroNome = "Ana";
        String sobrenome = "Bell";
        String telefone = "933434445";
        Genero genero = Genero.FEMININO;
        LocalDate dataNascimento = LocalDate.of(1990, 1, 1);
        String email = "ana@gmail.com";
        String senha = "asdf3523kkkdjjdfsdfghfghfvfgdff";
        EstadoConta estadoConta = EstadoConta.PENDENTE;
        Utilizador criadoPor = null;
        Utilizador aprovadoPor = null;

        TipoProfissional tipo = TipoProfissional.PROFESSOR;
        Perfil perfil = Perfil.PROFESSOR;

        Escola escola = new Escola("School", "Rua francisco da silva", "923423445", "school@gmail.com");

        assertThrows(IllegalArgumentException.class, () -> {
            new UtilizadorProfissional(numeroIdentificacao, primeiroNome, sobrenome, telefone, genero, dataNascimento, email,
                    senha, tipo, perfil, escola, estadoConta, criadoPor, aprovadoPor);
        });
    }

    @Test
    void naoDeveCriarUtilizadorProfissionalComSenhaSemLetraMaiuscula() throws Exception{
        String numeroIdentificacao = "123456789";
        String primeiroNome = "Ana";
        String sobrenome = "Bell";
        String telefone = "933434445";
        Genero genero = Genero.FEMININO;
        LocalDate dataNascimento = LocalDate.of(1990, 1, 1);
        String email = "ana@gmail.com";
        String senha = "avante";
        EstadoConta estadoConta = EstadoConta.PENDENTE;
        Utilizador criadoPor = null;
        Utilizador aprovadoPor = null;

        TipoProfissional tipo = TipoProfissional.PROFESSOR;
        Perfil perfil = Perfil.PROFESSOR;

        Escola escola = new Escola("School", "Rua francisco da silva", "923423445", "school@gmail.com");

        assertThrows(IllegalArgumentException.class, () -> {
            new UtilizadorProfissional(numeroIdentificacao, primeiroNome, sobrenome, telefone, genero, dataNascimento, email,
                    senha, tipo, perfil, escola, estadoConta, criadoPor, aprovadoPor);
        });
    }

    @Test
    void naoDeveCriarUtilizadorProfissionalComSenhaSemLetraMinuscula() throws Exception{
        String numeroIdentificacao = "123456789";
        String primeiroNome = "Ana";
        String sobrenome = "Bell";
        String telefone = "933434445";
        Genero genero = Genero.FEMININO;
        LocalDate dataNascimento = LocalDate.of(1990, 1, 1);
        String email = "ana@gmail.com";
        String senha = "AVANTE";
        EstadoConta estadoConta = EstadoConta.PENDENTE;
        Utilizador criadoPor = null;
        Utilizador aprovadoPor = null;

        TipoProfissional tipo = TipoProfissional.PROFESSOR;
        Perfil perfil = Perfil.PROFESSOR;

        Escola escola = new Escola("School", "Rua francisco da silva", "923423445", "school@gmail.com");

        assertThrows(IllegalArgumentException.class, () -> {
            new UtilizadorProfissional(numeroIdentificacao, primeiroNome, sobrenome, telefone, genero, dataNascimento, email,
                    senha, tipo, perfil, escola, estadoConta, criadoPor, aprovadoPor);
        });
    }

    @Test
    void naoDeveCriarUtilizadorProfissionaComSenhaSemNumero() throws Exception{
        String numeroIdentificacao = "123456789";
        String primeiroNome = "Ana";
        String sobrenome = "Bell";
        String telefone = "933434445";
        Genero genero = Genero.FEMININO;
        LocalDate dataNascimento = LocalDate.of(1990, 1, 1);
        String email = "ana@gmail.com";
        String senha = "Avante";
        EstadoConta estadoConta = EstadoConta.PENDENTE;
        Utilizador criadoPor = null;
        Utilizador aprovadoPor = null;

        TipoProfissional tipo = TipoProfissional.PROFESSOR;
        Perfil perfil = Perfil.PROFESSOR;

        Escola escola = new Escola("School", "Rua francisco da silva", "923423445", "school@gmail.com");

        assertThrows(IllegalArgumentException.class, () -> {
            new UtilizadorProfissional(numeroIdentificacao, primeiroNome, sobrenome, telefone, genero, dataNascimento, email,
                    senha, tipo, perfil, escola, estadoConta, criadoPor, aprovadoPor);
        });
    }

    @Test
    void naoDeveCriarUtilizadorPrissionalComSenhaSemCaracteresEspeciais() throws Exception{
        String numeroIdentificacao = "123456789";
        String primeiroNome = "Ana";
        String sobrenome = "Bell";
        String telefone = "933434445";
        Genero genero = Genero.FEMININO;
        LocalDate dataNascimento = LocalDate.of(1990, 1, 1);
        String email = "ana@gmail.com";
        String senha = "@Avante";
        EstadoConta estadoConta = EstadoConta.PENDENTE;
        Utilizador criadoPor = null;
        Utilizador aprovadoPor = null;

        TipoProfissional tipo = TipoProfissional.PROFESSOR;
        Perfil perfil = Perfil.PROFESSOR;

        Escola escola = new Escola("School", "Rua francisco da silva", "924234445", "school@gmail.com");

        assertThrows(IllegalArgumentException.class, () -> {
            new UtilizadorProfissional(numeroIdentificacao, primeiroNome, sobrenome, telefone, genero, dataNascimento, email,
                    senha, tipo, perfil, escola, estadoConta, criadoPor, aprovadoPor);
        });
    }


    @Test
    void deveAceitarTelefoneValido() {
        String numeroIdentificacao = "123456789";
        String primeiroNome = "Ana";
        String sobrenome = "Bell";
        String telefone = "933434456";
        Genero genero = Genero.FEMININO;
        LocalDate dataNascimento = LocalDate.of(1990, 1, 1);
        String email = "ana@gmail.com";
        String senha = "@Avante";
        EstadoConta estadoConta = EstadoConta.PENDENTE;
        Utilizador criadoPor = null;
        Utilizador aprovadoPor = null;

        TipoProfissional tipo = TipoProfissional.PROFESSOR;
        Perfil perfil = Perfil.PROFESSOR;

        Escola escola = new Escola("School", "Rua francisco da silva", "924234445", "school@gmail.com");

        assertThrows(IllegalArgumentException.class, () -> {
            new UtilizadorProfissional(numeroIdentificacao, primeiroNome, sobrenome, telefone, genero, dataNascimento, email,
                    senha, tipo, perfil, escola, estadoConta, criadoPor, aprovadoPor);
        });
    }

    @Test
    void naoDeveAceitarTelefoneComMenosDeNoveDigitos() {
        String numeroIdentificacao = "123456789";
        String primeiroNome = "Ana";
        String sobrenome = "Bell";
        String telefone = "93343445";
        Genero genero = Genero.FEMININO;
        LocalDate dataNascimento = LocalDate.of(1990, 1, 1);
        String email = "ana@gmail.com";
        String senha = "@Avante";
        EstadoConta estadoConta = EstadoConta.PENDENTE;
        Utilizador criadoPor = null;
        Utilizador aprovadoPor = null;

        TipoProfissional tipo = TipoProfissional.PROFESSOR;
        Perfil perfil = Perfil.PROFESSOR;

        Escola escola = new Escola("School", "Rua francisco da silva", "924234445", "school@gmail.com");

        assertThrows(IllegalArgumentException.class, () -> {
            new UtilizadorProfissional(numeroIdentificacao, primeiroNome, sobrenome, telefone, genero, dataNascimento, email,
                    senha, tipo, perfil, escola, estadoConta, criadoPor, aprovadoPor);
        });
    }

    @Test
    void naoDeveAceitarTelefoneComMaisDeNoveDigitos() {
        String numeroIdentificacao = "123456789";
        String primeiroNome = "Ana";
        String sobrenome = "Bell";
        String telefone = "93343445654";
        Genero genero = Genero.FEMININO;
        LocalDate dataNascimento = LocalDate.of(1990, 1, 1);
        String email = "ana@gmail.com";
        String senha = "@Avante";
        EstadoConta estadoConta = EstadoConta.PENDENTE;
        Utilizador criadoPor = null;
        Utilizador aprovadoPor = null;

        TipoProfissional tipo = TipoProfissional.PROFESSOR;
        Perfil perfil = Perfil.PROFESSOR;

        Escola escola = new Escola("School", "Rua francisco da silva", "924234445", "school@gmail.com");

        assertThrows(IllegalArgumentException.class, () -> {
            new UtilizadorProfissional(numeroIdentificacao, primeiroNome, sobrenome, telefone, genero, dataNascimento, email,
                    senha, tipo, perfil, escola, estadoConta, criadoPor, aprovadoPor);
        });
    }

    @Test
    void naoDeveAceitarNumeroNulo(){
        String numeroIdentificacao = "123456789";
        String primeiroNome = "Ana";
        String sobrenome = "Bell";
        String telefone = null;
        Genero genero = Genero.FEMININO;
        LocalDate dataNascimento = LocalDate.of(1990, 1, 1);
        String email = "ana@gmail.com";
        String senha = "@Avante";
        EstadoConta estadoConta = EstadoConta.PENDENTE;
        Utilizador criadoPor = null;
        Utilizador aprovadoPor = null;

        TipoProfissional tipo = TipoProfissional.PROFESSOR;
        Perfil perfil = Perfil.PROFESSOR;

        Escola escola = new Escola("School", "Rua francisco da silva", "924234445", "school@gmail.com");

        assertThrows(IllegalArgumentException.class, () -> {
            new UtilizadorProfissional(numeroIdentificacao, primeiroNome, sobrenome, telefone, genero, dataNascimento, email,
                    senha, tipo, perfil, escola, estadoConta, criadoPor, aprovadoPor);
        });
    }

    @Test
    void naoDeveAceitarTelefoneComLetras(){
        String numeroIdentificacao = "123456789";
        String primeiroNome = "Ana";
        String sobrenome = "Bell";
        String telefone = "933434456AB";
        Genero genero = Genero.FEMININO;
        LocalDate dataNascimento = LocalDate.of(1990, 1, 1);
        String email = "ana@gmail.com";
        String senha = "@Avante";
        EstadoConta estadoConta = EstadoConta.PENDENTE;
        Utilizador criadoPor = null;
        Utilizador aprovadoPor = null;

        TipoProfissional tipo = TipoProfissional.PROFESSOR;
        Perfil perfil = Perfil.PROFESSOR;

        Escola escola = new Escola("School", "Rua francisco da silva", "924234445", "school@gmail.com");

        assertThrows(IllegalArgumentException.class, () -> {
            new UtilizadorProfissional(numeroIdentificacao, primeiroNome, sobrenome, telefone, genero, dataNascimento, email,
                    senha, tipo, perfil, escola, estadoConta, criadoPor, aprovadoPor);
        });
    }

    @Test
    void deveAceiraNumeroIdentificacaoValido(){
        String numeroIdentificacao = "123456789";
        String primeiroNome = "Ana";
        String sobrenome = "Bell";
        String telefone = "933434456";
        Genero genero = Genero.FEMININO;
        LocalDate dataNascimento = LocalDate.of(1990, 1, 1);
        String email = "ana@gmail.com";
        String senha = "@Avante";
        EstadoConta estadoConta = EstadoConta.PENDENTE;
        Utilizador criadoPor = null;
        Utilizador aprovadoPor = null;

        TipoProfissional tipo = TipoProfissional.PROFESSOR;
        Perfil perfil = Perfil.PROFESSOR;

        Escola escola = new Escola("School", "Rua francisco da silva", "924234445", "school@gmail.com");

        assertThrows(IllegalArgumentException.class, () -> {
            new UtilizadorProfissional(numeroIdentificacao, primeiroNome, sobrenome, telefone, genero, dataNascimento, email,
                    senha, tipo, perfil, escola, estadoConta, criadoPor, aprovadoPor);
        });
    }

    @Test
    void naoDeveAceitarNumeroIdentificacaoComMaisDeVinteCaracteres(){
        String numeroIdentificacao = "123456789A343Ad309123";
        String primeiroNome = "Ana";
        String sobrenome = "Bell";
        String telefone = "933434456";
        Genero genero = Genero.FEMININO;
        LocalDate dataNascimento = LocalDate.of(1990, 1, 1);
        String email = "ana@gmail.com";
        String senha = "@Avante";
        EstadoConta estadoConta = EstadoConta.PENDENTE;
        Utilizador criadoPor = null;
        Utilizador aprovadoPor = null;

        TipoProfissional tipo = TipoProfissional.PROFESSOR;
        Perfil perfil = Perfil.PROFESSOR;

        Escola escola = new Escola("School", "Rua francisco da silva", "924234445", "school@gmail.com");

        assertThrows(IllegalArgumentException.class, () -> {
            new UtilizadorProfissional(numeroIdentificacao, primeiroNome, sobrenome, telefone, genero, dataNascimento, email,
                    senha, tipo, perfil, escola, estadoConta, criadoPor, aprovadoPor);
        });
    }
}
