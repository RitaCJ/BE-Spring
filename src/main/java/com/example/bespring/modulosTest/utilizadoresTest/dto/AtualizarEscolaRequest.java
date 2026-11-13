package com.example.bespring.modulosTest.utilizadoresTest.dto;

public record AtualizarEscolaRequest(
        String nome,
        String endereco,
        String email,
        String telefone
) { }
