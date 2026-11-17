package com.example.bespring.modulosTest.utilizadoresTest.domainTests;

import com.example.bespring.modulosTest.utilizadoresTest.domain.Escola;
import com.example.bespring.modulosTest.utilizadoresTest.domain.Psicologo;
import com.example.bespring.modulosTest.utilizadoresTest.domain.Utilizador;
import com.example.bespring.modulosTest.utilizadoresTest.domain.enums.EstadoConta;
import com.example.bespring.modulosTest.utilizadoresTest.domain.enums.Genero;
import com.example.bespring.modulosTest.utilizadoresTest.domain.enums.Perfil;
import com.example.bespring.modulosTest.utilizadoresTest.domain.enums.TipoProfissional;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PsicologoDomainTest {

    @Test
    void deveCriarPsicologoComCamposValidos(){

        String numeroIdentificacao = "123456789";
        String primeiroNome = "Adeben";
        String sobrenome = "Akello";
        String telefone = "94533443";
        Genero genero = Genero.MASCULINO;
        LocalDate dataNascimento = LocalDate.of(1990, 1, 1);
        String email = "adeben@gmail.com";
        String senha = "SereiasNaoExistem!";
        TipoProfissional tipo = TipoProfissional.PSICOLOGO;
        Perfil perfil = Perfil.PSICOLOGO;
        EstadoConta estadoConta = EstadoConta.PENDENTE;
        Utilizador criadoPor = null;
        Utilizador aprovadoPor = null;

        Escola escola = new Escola("School", "Rua francisco da silva", "9234234445", "school@gmail.com");

        Psicologo psicologo = new Psicologo(numeroIdentificacao, primeiroNome, sobrenome, telefone, genero, dataNascimento, email,
                senha, tipo, perfil, escola, estadoConta, criadoPor, aprovadoPor);

        assertEquals(numeroIdentificacao, psicologo.getNumeroIdentificacao());
        assertEquals(primeiroNome, psicologo.getPrimeiroNome());
        assertEquals(sobrenome, psicologo.getSobrenome());
        assertEquals(telefone, psicologo.getTelefone());
        assertEquals(genero, psicologo.getGenero());
        assertEquals(dataNascimento, psicologo.getDataNascimento());
        assertEquals(email, psicologo.getEmail());
        assertEquals(senha, psicologo.getSenha());
        assertEquals(tipo, psicologo.getTipo());
        assertEquals(perfil, psicologo.getPerfil());
        assertEquals(escola, psicologo.getEscola());
        assertEquals(estadoConta, psicologo.getEstadoConta());
        assertEquals(criadoPor, psicologo.getCriadoPor());
        assertEquals(aprovadoPor, psicologo.getAprovadoPor());
    }
}
