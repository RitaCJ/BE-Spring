package com.example.bespring.controllers;

import com.example.bespring.domain.Escola;
import com.example.bespring.domain.Professor;
import com.example.bespring.domain.enums.Genero;
import com.example.bespring.domain.enums.Perfil;
import com.example.bespring.domain.enums.TipoProfissional;
import com.example.bespring.dto.CriarProfessorRequest;
import com.example.bespring.dto.CriarProfessorResponse;
import com.example.bespring.services.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/api/professores")
public class ProfessorController {

    private final ProfessorService professorService;

    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @PostMapping()
    public ResponseEntity<CriarProfessorResponse> cadastrarProfessor(@RequestBody CriarProfessorRequest criarProfessorRequest) {

        Professor professor = professorService.cadastrarProfessor(criarProfessorRequest);

        CriarProfessorResponse professorResponse = new CriarProfessorResponse(
                professor.getIdUtilizador(),
                professor.getPrimeiroNome(),
                professor.getSobrenome(),
                professor.getTelefone(),
                professor.getGenero(),
                professor.getEmail(),
                professor.getTipo(),
                professor.getPerfil(),
                professor.getEscola().getIdEscola()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(professorResponse);
    }
}
