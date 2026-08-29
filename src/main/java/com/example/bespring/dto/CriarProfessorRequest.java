package com.example.bespring.dto;

import com.example.bespring.domain.Escola;
import com.example.bespring.domain.enums.Genero;
import com.example.bespring.domain.enums.Perfil;
import com.example.bespring.domain.enums.TipoProfissional;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record CriarProfessorRequest(
        String primeiroNome,
        String sobrenome,
        String telefone,
        Genero genero,
        String email,
        String senha,
        TipoProfissional tipo,
        Perfil perfil,
        Escola escola
) { }
