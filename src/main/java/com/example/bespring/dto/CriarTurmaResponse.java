package com.example.bespring.dto;

import java.util.List;

public record CriarTurmaResponse(
        long idTurma,
        String nome,
        String sala,
        int anoLetivo,
        String anoSerie,
        String descricao,
        List<Long> idAlunos,
        long idProfessor
) { }
