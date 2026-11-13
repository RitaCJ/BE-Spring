package com.example.bespring.modulosTest.utilizadoresTest.dto;

import com.example.bespring.modulosTest.utilizadoresTest.domain.enums.Genero;

public record CriarAlunoRequest(
        String primeiroNome,
        String sobrenome,
        String nomeUtilizador,
        int numeroAluno,
        String sala,
        String corFavorita,
        boolean possuiDaltonismo,
        Genero genero,
        String senha,
        Long idEscola
) { }
