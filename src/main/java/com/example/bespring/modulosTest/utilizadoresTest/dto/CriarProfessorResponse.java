package com.example.bespring.modulosTest.utilizadoresTest.dto;

import com.example.bespring.modulosTest.utilizadoresTest.domain.enums.Genero;
import com.example.bespring.modulosTest.utilizadoresTest.domain.enums.Perfil;
import com.example.bespring.modulosTest.utilizadoresTest.domain.enums.TipoProfissional;

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
