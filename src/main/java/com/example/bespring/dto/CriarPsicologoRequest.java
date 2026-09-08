package com.example.bespring.dto;

import com.example.bespring.domain.enums.Genero;
import com.example.bespring.domain.enums.Perfil;
import com.example.bespring.domain.enums.TipoProfissional;

public record CriarPsicologoRequest(
        String primeiroNome,
        String sobrenome,
        String telefone,
        Genero genero,
        String email,
        String senha,
        TipoProfissional tipo,
        Long idEscola
) { }
