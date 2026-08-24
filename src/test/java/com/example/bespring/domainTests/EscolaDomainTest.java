package com.example.bespring.domainTests;

import com.example.bespring.domain.Escola;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class EscolaDomainTest {

    @Test
    void deveCriarUmaEscolaComDadosValidos() {

        //Given - Preparar os dados (condições de teste).
        String nome = "School";
        String endereco = "Rua do Antonio";
        String email = "school@gmail.com";
        String telefone = "123456789";

        //When - Executar a acção que queres testar.
        Escola escola = new Escola(nome, endereco, telefone, email);

        //Then - Verificar o resultado esperado.
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

    @Test
    void naoDeveCriarEscolaComEnderecoVazio() throws Exception {

        assertThrows(IllegalArgumentException.class, () ->
                new Escola("School", " ", "9234234445", "school@gmail.com")
        );
    }

    @Test
    void naoDeveCriarEscolaComTelefoneVazio() throws Exception {

        assertThrows(IllegalArgumentException.class, () ->
            new Escola("School", "Rua Antonio Fran", " ", "school@gmail.com")
        );
    }

    @Test
    void naoDeveCriarEscolaComEmailVazio() throws Exception {

        assertThrows(IllegalArgumentException.class, () ->
            new Escola("School", "Rua Antonio Fran", "9233445553", "")
        );
    }

}
