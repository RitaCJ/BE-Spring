package com.example.bespring.modulosTest.utilizadoresTest.controllers;

import com.example.bespring.modulosTest.utilizadoresTest.domain.Professor;
import com.example.bespring.modulosTest.utilizadoresTest.dto.AtualizarProfessorRequest;
import com.example.bespring.modulosTest.utilizadoresTest.dto.CriarProfessorRequest;
import com.example.bespring.modulosTest.utilizadoresTest.dto.CriarProfessorResponse;
import com.example.bespring.modulosTest.utilizadoresTest.services.ProfessorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

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
                professor.getNumeroIdentificacao(),
                professor.getPrimeiroNome(),
                professor.getSobrenome(),
                professor.getTelefone(),
                professor.getGenero(),
                professor.getDataNascimento(),
                professor.getEmail(),
                professor.getTipo(),
                professor.getPerfil(),
                professor.getEscola().getIdEscola(),
                professor.getEstadoConta(),
                professor.getCriadoPor(),
                professor.getAprovadoPor()
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
                professor.getNumeroIdentificacao(),
                professor.getPrimeiroNome(),
                professor.getSobrenome(),
                professor.getTelefone(),
                professor.getGenero(),
                professor.getDataNascimento(),
                professor.getEmail(),
                professor.getTipo(),
                professor.getPerfil(),
                professor.getEscola().getIdEscola(),
                professor.getEstadoConta(),
                professor.getCriadoPor(),
                professor.getAprovadoPor()
        );

        return ResponseEntity.status(HttpStatus.OK).body(professorResponse);
    }

    @GetMapping
    public ResponseEntity<List<CriarProfessorResponse>> listarProfessores() {

        List<Professor> professores = professorService.listarProfessores();

        List<CriarProfessorResponse> professoresResponse = professores.stream()
                .map(professor -> new CriarProfessorResponse(
                        professor.getIdUtilizador(),
                        professor.getNumeroIdentificacao(),
                        professor.getPrimeiroNome(),
                        professor.getSobrenome(),
                        professor.getTelefone(),
                        professor.getGenero(),
                        professor.getDataNascimento(),
                        professor.getEmail(),
                        professor.getTipo(),
                        professor.getPerfil(),
                        professor.getEscola().getIdEscola(),
                        professor.getEstadoConta(),
                        professor.getCriadoPor(),
                        professor.getAprovadoPor()
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
    public ResponseEntity<Void> deletarProfessor(@PathVariable Long idUtilizador,
                                                 Authentication authentication) {

        String userLogado = authentication.getName();

        professorService.apagarProfessor(idUtilizador, userLogado);

        return ResponseEntity.noContent().build();

    }
}
