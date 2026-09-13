package com.example.bespring.dto;

import java.util.List;

public record CriarTurmaRequest(
        String nome,
        String sala,
        int anoLetivo,
        String anoSerie,
        String descricao,
        List<Long> idAlunos
) { }
