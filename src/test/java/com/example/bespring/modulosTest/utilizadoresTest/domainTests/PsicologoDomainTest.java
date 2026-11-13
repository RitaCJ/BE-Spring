package com.example.bespring.modulosTest.utilizadoresTest.domainTests;

import com.example.bespring.modulosTest.utilizadoresTest.domain.Escola;
import com.example.bespring.modulosTest.utilizadoresTest.domain.Psicologo;
import com.example.bespring.modulosTest.utilizadoresTest.domain.enums.Genero;
import com.example.bespring.modulosTest.utilizadoresTest.domain.enums.Perfil;
import com.example.bespring.modulosTest.utilizadoresTest.domain.enums.TipoProfissional;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PsicologoDomainTest {

    @Test
    void deveCriarPsicologoComCamposValidos(){

        String primeiroNome = "Adeben";
        String sobrenome = "Akello";
        String telefone = "94533443";
        Genero genero = Genero.MASCULINO;
        String email = "adeben@gmail.com";
        String senha = "SereiasNaoExistem!";
        TipoProfissional tipo = TipoProfissional.PSICOLOGO;
        Perfil perfil = Perfil.PSICOLOGO;

        Escola escola = new Escola("School", "Rua francisco da silva", "9234234445", "school@gmail.com");

        Psicologo psicologo = new Psicologo(primeiroNome, sobrenome, telefone, genero, email, senha, tipo, perfil, escola);

        assertEquals(primeiroNome, psicologo.getPrimeiroNome());
        assertEquals(sobrenome, psicologo.getSobrenome());
        assertEquals(telefone, psicologo.getTelefone());
        assertEquals(genero, psicologo.getGenero());
        assertEquals(email, psicologo.getEmail());
        assertEquals(senha, psicologo.getSenha());
        assertEquals(tipo, psicologo.getTipo());
        assertEquals(perfil, psicologo.getPerfil());
        assertEquals(escola, psicologo.getEscola());

    }

}
