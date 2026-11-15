package com.example.bespring.modulosTest.utilizadoresTest.dto;

import com.example.bespring.modulosTest.utilizadoresTest.domain.enums.EstadoConta;
import com.example.bespring.modulosTest.utilizadoresTest.domain.enums.Genero;
import com.example.bespring.modulosTest.utilizadoresTest.domain.enums.Perfil;

import java.time.LocalDate;

public record AtualizarAdminEscolaRequest(

        String primeiroNome,
        String sobrenome,
        Genero genero,
        String senha,
        String login
) { }
