package com.example.bespring.modulosTest.utilizadoresTest.domainTests;

import com.example.bespring.modulosTest.utilizadoresTest.domain.Escola;
import com.example.bespring.modulosTest.utilizadoresTest.dto.NumeroTelefoneDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


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
            new Escola("School", "Rua Antonio Fran", "923344555", "")
        );
    }

    @Test
    void deveAceitarTelefoneValido() {
        Escola escola = new Escola(
                "School",
                "Rua Antonio Fran",
                "923432123",
                "antonio@gmail.com"
        );

        assertEquals("923432123", escola.getTelefone());
    }

    @Test
    void naoDeveAceitarTelefoneComMenosDeNoveDigitos() {
        assertThrows(IllegalArgumentException.class, () ->
                new Escola(
                        "School",
                        "Rua Antonio Fran",
                        "92343212",
                        "antonio@gmail.com")
        );
    }

    @Test
    void naoDeveAceitarTelefoneComMaisDeNoveDigitos() {
        assertThrows(IllegalArgumentException.class, () ->
                new Escola(
                        "School",
                        "Rua Antonio Fran",
                        "9234321245",
                        "antonio@gmail.com")
        );
    }

    @Test
    void naoDeveAceitarNumeroNulo(){
        assertThrows(IllegalArgumentException.class, () ->
                new Escola(
                        "School",
                        "Rua Antonio Fran",
                        null,
                        "antonio@gmail.com")
        );
    }

    @Test
    void naoDeveAceitarTelefoneComLetras(){
        assertThrows(IllegalArgumentException.class, () ->
                new Escola(
                        "School",
                        "Rua Antonio Fran",
                        "923432123A",
                        "antonio@gmail.com")
        );
    }
}
