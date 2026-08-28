package com.example.bespring.domainTests;

import com.example.bespring.domain.Escola;
import com.example.bespring.domain.Professor;
import com.example.bespring.domain.UtilizadorProfissional;
import com.example.bespring.domain.enums.Genero;
import com.example.bespring.domain.enums.Perfil;
import com.example.bespring.domain.enums.TipoProfissional;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProfessorDomainTest {

    @Test
    void deveCriarUmProfessorComCamposValidos() {
        String primeiroNome = "Ana";
        String sobrenome = "Bell";
        String telefone = "933434445";
        Genero genero = Genero.FEMININO;
        String email = "ana@gmail.com";
        String senha = "Feliznatal2026%";
        TipoProfissional tipo = TipoProfissional.PROFESSOR;
        Perfil perfil = Perfil.PROFESSOR;

        Escola escola = new Escola("School", "Rua francisco da silva", "9234234445", "school@gmail.com");

        Professor professor = new Professor(primeiroNome, sobrenome, telefone, genero, email, senha, tipo, perfil, escola);

        assertEquals(primeiroNome, professor.getPrimeiroNome());
        assertEquals(sobrenome, professor.getSobrenome());
        assertEquals(telefone, professor.getTelefone());
        assertEquals(genero, professor.getGenero());
        assertEquals(email, professor.getEmail());
        assertEquals(senha, professor.getSenha());
        assertEquals(tipo, professor.getTipo());
        assertEquals(perfil, professor.getPerfil());
        assertEquals(escola, professor.getEscola());
    }




}
