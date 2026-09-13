package com.example.bespring.dto;

import com.example.bespring.domain.enums.Genero;
import com.example.bespring.domain.enums.TipoProfissional;

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
