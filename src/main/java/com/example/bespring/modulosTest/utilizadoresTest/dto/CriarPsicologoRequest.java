package com.example.bespring.modulosTest.utilizadoresTest.dto;

import com.example.bespring.modulosTest.utilizadoresTest.domain.enums.Genero;
import com.example.bespring.modulosTest.utilizadoresTest.domain.enums.TipoProfissional;

public record CriarPsicologoRequest(
        String primeiroNome,
        String sobrenome,
        String telefone,
        Genero genero,
        String email,
        String senha,
        TipoProfissional tipo,
        Long idEscola
) { }
