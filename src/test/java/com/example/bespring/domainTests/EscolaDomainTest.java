package com.example.bespring.domainTests;

import com.example.bespring.domain.Escola;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class EscolaDomainTest {

    @Test
    void deveRetornarDadosValidosParaUmaEscola() throws Exception {

        String nome = "School";
        String endereco = "Rua do Antonio";
        String email = "school@gmail.com";
        String telefone = "123456789";

        Escola escola = new Escola(nome, endereco, telefone, email);

        assertEquals(nome, escola.getNome());
        assertEquals(endereco, escola.getEndereco());
        assertEquals(telefone, escola.getTelefone());
        assertEquals(email, escola.getEmail());

    }

    @Test
    void naoDeveCriarEscolaComNomeVazio() throws Exception {

        String nome = "";
        String endereco = "Rua do Antonio";
        String email = "school@gmail.com";
        String telefone = "123456789";
       // Escola escola = new Escola(nome, endereco, telefone, email);

        assertThrows(IllegalArgumentException.class, () ->
            new Escola(nome, endereco, telefone, email)
        );

    }

}
