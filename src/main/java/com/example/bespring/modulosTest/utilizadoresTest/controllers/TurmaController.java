package com.example.bespring.modulosTest.utilizadoresTest.controllers;

import com.example.bespring.modulosTest.utilizadoresTest.domain.Aluno;
import com.example.bespring.modulosTest.utilizadoresTest.domain.Professor;
import com.example.bespring.modulosTest.utilizadoresTest.domain.Turma;
import com.example.bespring.modulosTest.utilizadoresTest.dto.CriarTurmaRequest;
import com.example.bespring.modulosTest.utilizadoresTest.dto.CriarTurmaResponse;
import com.example.bespring.modulosTest.utilizadoresTest.services.TurmaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping("api/turmas")
public class TurmaController {

    private final TurmaService turmaService;


    public TurmaController(TurmaService turmaService) {
        this.turmaService = turmaService;

    }

    @PostMapping()
    public ResponseEntity<CriarTurmaResponse> criarTurma(@RequestBody @Valid CriarTurmaRequest criarTurmaRequest,
                                                         Authentication authentication) {

        Professor professor = (Professor) authentication.getPrincipal();

        long idProfessorLogado = professor.getIdUtilizador();


        System.out.println("Utilizador logado: " + idProfessorLogado);


        Turma turma = turmaService.criarTurma(criarTurmaRequest, idProfessorLogado);

        CriarTurmaResponse turmaResponse = new CriarTurmaResponse(
                turma.getIdTurma(),
                turma.getNome(),
                turma.getSala(),
                turma.getAnoLetivo(),
                turma.getAnoSerie(),
                turma.getDescricao(),
                turma.getAlunos().stream()
                        .map(Aluno::getIdUtilizador)
                        .collect(Collectors.toList()),
                turma.getProfessor().getIdUtilizador()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(turmaResponse);

    }



}
