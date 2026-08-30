package com.example.bespring.dto;

import com.example.bespring.domain.Escola;
import com.example.bespring.domain.enums.Genero;
import com.example.bespring.domain.enums.Perfil;
import com.example.bespring.domain.enums.TipoProfissional;

public record CriarProfessorResponse(
        Long idUtilizador,
        String primeiroNome,
        String sobrenome,
        String telefone,
        Genero genero,
        String email,
        TipoProfissional tipo,
        Perfil perfil,
        Long idEscola
) { }
