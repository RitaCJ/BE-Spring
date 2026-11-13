package com.example.bespring.modulosTest.utilizadoresTest.controllers;

import com.example.bespring.modulosTest.utilizadoresTest.domain.Aluno;
import com.example.bespring.modulosTest.utilizadoresTest.domain.Professor;
import com.example.bespring.modulosTest.utilizadoresTest.domain.Turma;
import com.example.bespring.modulosTest.utilizadoresTest.dto.AtualizarAlunoRequest;
import com.example.bespring.modulosTest.utilizadoresTest.dto.CriarAlunoRequest;
import com.example.bespring.modulosTest.utilizadoresTest.dto.CriarAlunoResponse;
import com.example.bespring.modulosTest.utilizadoresTest.services.AlunoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/alunos")
public class AlunoController {

    private final AlunoService alunoService;


    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;

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

    @GetMapping
    public ResponseEntity<List<CriarAlunoResponse>> listarAlunos(Authentication authentication) {

        Professor professor = (Professor) authentication.getPrincipal();

        assert professor != null;
        long idProfessorLogado = professor.getIdUtilizador();

        List<Aluno> alunos = alunoService.listarAlunos(idProfessorLogado);

        Long turmaId;
//
//        if(!alunos.isEmpty()) {
//            turmaId = alunos.getFirst().getTurma().getIdTurma();
//        } else {
//            turmaId = null;
//        }

        if(alunos != null && !alunos.isEmpty()) {
            turmaId = alunos.stream()
                    .filter(Objects::nonNull)
                    .map(Aluno::getTurma)
                    .filter(Objects::nonNull)
                    .map(Turma::getIdTurma)
                    .filter(Objects::nonNull)
                    .findFirst()
                    .orElse(null);
        } else {
            turmaId = null;
        }


        List<CriarAlunoResponse> alunoResponse = alunos.stream()
                .map(aluno -> new CriarAlunoResponse(
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
                )).toList();

        return ResponseEntity.status(HttpStatus.OK).body(alunoResponse);
    }

    @GetMapping("/{idUtilizador}")
    public ResponseEntity<CriarAlunoResponse> buscarProfessor(@PathVariable Long idUtilizador,
                                                                  Authentication  authentication) {

        Professor professor = (Professor) authentication.getPrincipal();

        assert professor != null;
        long idProfessorLogado = professor.getIdUtilizador();

        Aluno aluno = alunoService.procurarAlunoPorId(idUtilizador, idProfessorLogado);

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

        return ResponseEntity.status(HttpStatus.OK).body(alunoResponse);
    }

    @PutMapping("/{idUtilizador}")
    public ResponseEntity<Void> atualizarAluno(@PathVariable Long idUtilizador,
                                                   @RequestBody AtualizarAlunoRequest atualizarAlunoRequest,
                                                   Authentication authentication) {
        Professor professor = (Professor) authentication.getPrincipal();

        assert professor != null;
        long idProfessorLogado = professor.getIdUtilizador();

        alunoService.atualizarAluno(idUtilizador, atualizarAlunoRequest, idProfessorLogado);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }


    @DeleteMapping("/{idUtilizador}")
    public ResponseEntity<Void> deletarAluno(@PathVariable Long idUtilizador,
                                                 Authentication authentication) {

        Professor professor = (Professor) authentication.getPrincipal();

        assert professor != null;
        long idProfessorLogado = professor.getIdUtilizador();

        alunoService.apagarAluno(idUtilizador, idProfessorLogado);

        return ResponseEntity.noContent().build();

    }
}
