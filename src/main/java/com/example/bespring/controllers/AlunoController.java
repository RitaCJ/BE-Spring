package com.example.bespring.controllers;

import com.example.bespring.domain.Aluno;
import com.example.bespring.domain.Professor;
import com.example.bespring.dto.CriarAlunoRequest;
import com.example.bespring.dto.CriarAlunoResponse;
import com.example.bespring.dto.CriarProfessorRequest;
import com.example.bespring.dto.CriarProfessorResponse;
import com.example.bespring.services.AlunoService;
import com.example.bespring.services.ProfessorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/alunos")
public class AlunoController {

    private final AlunoService alunoService;
    private final ProfessorService professorService;

    public AlunoController(AlunoService alunoService, ProfessorService professorService) {
        this.alunoService = alunoService;
        this.professorService = professorService;
    }

    //@PreAuthorize("hasRole('PROFESSOR')")
    @PostMapping()
    public ResponseEntity<CriarAlunoResponse> registarAluno(@RequestBody @Valid CriarAlunoRequest criarAlunoRequest,
                                                            Authentication authentication) {


        Professor professor = (Professor) authentication.getPrincipal();

        long idProfessorLogado = professor.getIdUtilizador();

        System.out.println("Utilizador logado: " + idProfessorLogado);

        Aluno aluno = alunoService.registarAluno(criarAlunoRequest, idProfessorLogado);

        System.out.println("Recebido " + aluno.isPossuiDaltonismo());

        Long turmaId = null;

        if(aluno.getTurma() != null) {
            turmaId = aluno.getTurma().getIdTurma();
        }

        CriarAlunoResponse alunoResponse = new CriarAlunoResponse(
                aluno.getIdUtilizador(),
                aluno.getPrimeiroNome(),
                aluno.getSobrenome(),
                aluno.getNomeUtilizador(),
                aluno.getNumeroAluno(),
                aluno.getSala(),
                aluno.getCorFavorita(),
                aluno.isPossuiDaltonismo(),
                aluno.getGenero(),
                aluno.getPerfil(),
                aluno.getEscola().getIdEscola(),
                turmaId,
                aluno.getProfessor().getIdUtilizador()
        );

        System.out.println("Recebido2 " + alunoResponse.possuiDaltonismo());

        return ResponseEntity.status(HttpStatus.CREATED).body(alunoResponse);
    }

}
