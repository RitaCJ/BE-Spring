package com.example.bespring.modulosTest.utilizadoresTest.domainTests;

import com.example.bespring.modulosTest.utilizadoresTest.domain.Escola;
import com.example.bespring.modulosTest.utilizadoresTest.domain.Professor;
import com.example.bespring.modulosTest.utilizadoresTest.domain.Utilizador;
import com.example.bespring.modulosTest.utilizadoresTest.domain.UtilizadorProfissional;
import com.example.bespring.modulosTest.utilizadoresTest.domain.enums.EstadoConta;
import com.example.bespring.modulosTest.utilizadoresTest.domain.enums.Genero;
import com.example.bespring.modulosTest.utilizadoresTest.domain.enums.Perfil;
import com.example.bespring.modulosTest.utilizadoresTest.domain.enums.TipoProfissional;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProfessorDomainTest {

    @Test
    void deveCriarUmProfessorComCamposValidos() {
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
}
