package com.example.bespring.modulosTest.utilizadoresTest.dto;

import com.example.bespring.modulosTest.utilizadoresTest.domain.enums.Genero;
import com.example.bespring.modulosTest.utilizadoresTest.domain.enums.TipoProfissional;

import java.time.LocalDate;

public record CriarProfessorRequest(
        String numeroIdentificacao,
        String primeiroNome,
        String sobrenome,
        String telefone,
        Genero genero,
        LocalDate dataNascimento,
        String email,
        String senha,
        TipoProfissional tipo,
        Long idEscola
) { }
