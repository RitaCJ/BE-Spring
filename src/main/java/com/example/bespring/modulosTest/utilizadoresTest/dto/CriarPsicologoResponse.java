package com.example.bespring.modulosTest.utilizadoresTest.dto;

import com.example.bespring.modulosTest.utilizadoresTest.domain.Utilizador;
import com.example.bespring.modulosTest.utilizadoresTest.domain.enums.EstadoConta;
import com.example.bespring.modulosTest.utilizadoresTest.domain.enums.Genero;
import com.example.bespring.modulosTest.utilizadoresTest.domain.enums.Perfil;
import com.example.bespring.modulosTest.utilizadoresTest.domain.enums.TipoProfissional;

import java.time.LocalDate;

public record CriarPsicologoResponse(
        Long idUtilizador,
        String numeroIdentificacao,
        String primeiroNome,
        String sobrenome,
        String telefone,
        Genero genero,
        LocalDate dataNascimento,
        String email,
        TipoProfissional tipo,
        Perfil perfil,
        Long idEscola,
        EstadoConta estadoConta,
        Utilizador criadoPor,
        Utilizador aprovadoPor
) { }
