package com.example.bespring.domainTests;

import com.example.bespring.domain.Escola;
import com.example.bespring.domain.Utilizador;
import com.example.bespring.domain.enums.Genero;
import com.example.bespring.domain.enums.Perfil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UtilizadorDomainTest {

    @Test
    void deveCriarUmUtilizadorComCamposValidos() {

        String primeiroNome = "Asa";
        String sobrenome = "Zuri";
        Genero genero = Genero.FEMININO;
        Perfil perfil = Perfil.ALUNO;

        Escola escola = new Escola("School", "Rua francisco da silva", "9234234445", "school@gmail.com");

        Utilizador utilizador = new Utilizador(primeiroNome, sobrenome, genero, perfil);

        assertEquals(primeiroNome, utilizador.getPrimeiroNome());
        assertEquals(sobrenome, utilizador.getSobrenome());
        assertEquals(genero, utilizador.getGenero());
        assertEquals(perfil, utilizador.getPerfil());


    }

}
