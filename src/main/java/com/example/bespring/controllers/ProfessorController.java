package com.example.bespring.controllers;

import com.example.bespring.domain.Escola;
import com.example.bespring.domain.Professor;
import com.example.bespring.domain.enums.Genero;
import com.example.bespring.domain.enums.Perfil;
import com.example.bespring.domain.enums.TipoProfissional;
import com.example.bespring.dto.AtualizarProfessorRequest;
import com.example.bespring.dto.CriarProfessorRequest;
import com.example.bespring.dto.CriarProfessorResponse;
import com.example.bespring.services.ProfessorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/professores")
public class ProfessorController {

    private final ProfessorService professorService;

    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @PostMapping()
    public ResponseEntity<CriarProfessorResponse> cadastrarProfessor(@RequestBody @Valid CriarProfessorRequest criarProfessorRequest) {

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

   // @PreAuthorize("#idUtilizador == authentication.principal.id")
    @GetMapping("/{idUtilizador}")
    public ResponseEntity<CriarProfessorResponse> buscarProfessor(@PathVariable Long idUtilizador,
                                                                  Authentication  authentication) {

        Authentication auth = authentication;

        String utilizadorLogado = auth.getName();

        Professor professor = professorService.procurarProfessorPorId(idUtilizador, utilizadorLogado);

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

        return ResponseEntity.status(HttpStatus.OK).body(professorResponse);
    }

    @GetMapping
    public ResponseEntity<List<CriarProfessorResponse>> listarProfessores() {

        List<Professor> professores = professorService.listarProfessores();

        List<CriarProfessorResponse> professoresResponse = professores.stream()
                .map(professor -> new CriarProfessorResponse(
                        professor.getIdUtilizador(),
                        professor.getPrimeiroNome(),
                        professor.getSobrenome(),
                        professor.getTelefone(),
                        professor.getGenero(),
                        professor.getEmail(),
                        professor.getTipo(),
                        professor.getPerfil(),
                        professor.getEscola().getIdEscola()
                )).toList();

        return ResponseEntity.status(HttpStatus.OK).body(professoresResponse);
    }

    @PutMapping("/{idUtilizador}")
    public ResponseEntity<Void> atualizarProfessor(@PathVariable Long idUtilizador,
                                                   @RequestBody AtualizarProfessorRequest atualizarProfessorRequest,
                                                   Authentication authentication) {
        System.out.println("Authentication " + authentication);
        //Pegar o professor que se encontra autenticado.
        String userLogado = authentication.getName();

         professorService.atualizarProfessor(idUtilizador, atualizarProfessorRequest, userLogado);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    @DeleteMapping("/{idUtilizador}")
    public ResponseEntity<Void> deletarProfessor(@PathVariable Long idUtilizador) {
        professorService.apagarProfessor(idUtilizador);

        return ResponseEntity.noContent().build();

    }
}
