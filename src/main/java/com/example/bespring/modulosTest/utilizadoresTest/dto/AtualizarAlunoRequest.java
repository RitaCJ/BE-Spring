package com.example.bespring.modulosTest.utilizadoresTest.dto;

import com.example.bespring.modulosTest.utilizadoresTest.domain.enums.Genero;

public record AtualizarAlunoRequest(

        String primeiroNome,
        String sobrenome,
        String nomeUtilizador,
        Integer numeroAluno,
        String sala,
        String corFavorita,
        Boolean possuiDaltonismo,
        Genero genero,
        String senha
) { }
