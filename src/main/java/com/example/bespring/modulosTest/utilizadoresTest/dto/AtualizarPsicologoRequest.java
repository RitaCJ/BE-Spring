package com.example.bespring.modulosTest.utilizadoresTest.dto;

import com.example.bespring.modulosTest.utilizadoresTest.domain.enums.Genero;

public record AtualizarPsicologoRequest(
        String primeiroNome,
        String sobrenome,
        String telefone,
        Genero genero,
        String email,
        String senha
) { }
