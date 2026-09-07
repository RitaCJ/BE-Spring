package com.example.bespring.controllers;

import com.example.bespring.domain.Psicologo;
import com.example.bespring.dto.CriarProfessorRequest;
import com.example.bespring.dto.CriarProfessorResponse;
import com.example.bespring.dto.CriarPsicologoRequest;
import com.example.bespring.dto.CriarPsicologoResponse;
import com.example.bespring.services.PsicologoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/psicologos")
public class PsicologoController {

    private final PsicologoService psicologoService;

    public PsicologoController(PsicologoService psicologoService) {
        this.psicologoService = psicologoService;
    }

    @PostMapping
    public ResponseEntity<CriarPsicologoResponse> cadastrarPsicologo(@RequestBody @Valid CriarPsicologoRequest psicologoRequest){

        Psicologo psicologo = psicologoService.cadastrarPsicologo(psicologoRequest);

        CriarPsicologoResponse psicologoResponse = new CriarPsicologoResponse(
                psicologo.getIdUtilizador(),
                psicologo.getPrimeiroNome(),
                psicologo.getSobrenome(),
                psicologo.getTelefone(),
                psicologo.getGenero(),
                psicologo.getEmail(),
                psicologo.getTipo(),
                psicologo.getPerfil(),
                psicologo.getEscola().getIdEscola()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(psicologoResponse);

    }
}
