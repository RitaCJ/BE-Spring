package com.example.bespring.modulosTest.utilizadoresTest.dto;

import com.example.bespring.modulosTest.utilizadoresTest.domain.enums.Genero;

import java.time.LocalDate;

public record CriarAdminEscolaRequest(
        String primeiroNome,
        String sobrenome,
        Genero genero,
        LocalDate dataNascimento,
        String login,
        String senha,
        Long idEscola
) { }


