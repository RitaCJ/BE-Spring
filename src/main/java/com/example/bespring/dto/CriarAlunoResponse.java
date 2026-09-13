package com.example.bespring.dto;

import com.example.bespring.domain.enums.Genero;
import com.example.bespring.domain.enums.Perfil;

public record CriarAlunoResponse(
        long idUtilizador,
        String primeiroNome,
        String sobrenome,
        String nomeUtilizador,
        int numeroAluno,
        String sala,
        String corFavorita,
        boolean possuiDaltonismo,
        Genero genero,
        Perfil perfil,
        Long idEscola,
        Long idTurma,
        Long idProfessor
) { }
