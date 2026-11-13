package com.example.bespring.modulosTest.utilizadoresTest.dto;

public record CriarEscolaResponse(
        long idEscola,
        String nome,
        String endereco,
        String telefone,
        String email,
        Long administrador_id
) {
}
