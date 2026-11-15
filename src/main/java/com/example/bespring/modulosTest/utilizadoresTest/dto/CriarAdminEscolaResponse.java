package com.example.bespring.modulosTest.utilizadoresTest.dto;

import com.example.bespring.modulosTest.utilizadoresTest.domain.Utilizador;
import com.example.bespring.modulosTest.utilizadoresTest.domain.enums.EstadoConta;
import com.example.bespring.modulosTest.utilizadoresTest.domain.enums.Genero;
import com.example.bespring.modulosTest.utilizadoresTest.domain.enums.Perfil;

import java.time.LocalDate;

public record CriarAdminEscolaResponse(
        long idUtilizador,
        String primeiroNome,
        String sobrenome,
        Genero genero,
        Perfil perfil,
        LocalDate dataNascimento,
        Long idEscola,
        EstadoConta estadoConta
) { }
