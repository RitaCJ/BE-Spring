package com.example.bespring.services;

import com.example.bespring.domain.Aluno;
import com.example.bespring.domain.Escola;
import com.example.bespring.domain.Professor;
import com.example.bespring.domain.Turma;
import com.example.bespring.dto.CriarAlunoRequest;
import com.example.bespring.dto.CriarTurmaRequest;
import com.example.bespring.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurmaService {

    private final TurmaRepository turmaRepository;
    private final EscolaRepository escolaRepository;
    private final AlunoRepository alunoRepository;
    private final ProfessorRepository professorRepository;
    private final UtilizadorRepository utilizadorRepository;

    public TurmaService(TurmaRepository turmaRepository, EscolaRepository escolaRepository,
                        AlunoRepository alunoRepository, ProfessorRepository professorRepository,
                        UtilizadorRepository utilizadorRepository) {
        this.turmaRepository = turmaRepository;
        this.escolaRepository = escolaRepository;
        this.alunoRepository = alunoRepository;
        this.professorRepository = professorRepository;
        this.utilizadorRepository = utilizadorRepository;
    }

    public Turma criarTurma(CriarTurmaRequest turmaRequest, long idProfessor) {

        if(turmaRepository.existsByNome(turmaRequest.nome())){
            throw new RuntimeException("Já existe uma turma com esse nome");
        }

        Professor professor = professorRepository.findById(idProfessor)
                .orElseThrow(() -> new RuntimeException("Professor nao encontrado"));

        //Converte os ID para entidade alunos.
        List<Aluno> alunos = alunoRepository.findAllById(turmaRequest.idAlunos());

        Turma turmaEntity = new Turma(
                turmaRequest.nome(),
                turmaRequest.sala(),
                turmaRequest.anoLetivo(),
                turmaRequest.anoSerie(),
                turmaRequest.descricao(),
                professor
        );

        turmaEntity.setAlunos(alunos);

        var turma = turmaRepository.save(turmaEntity);

        for(Aluno aluno : alunos){
            aluno.setTurma(turma);
        }

        alunoRepository.saveAll(alunos);

        return turma;

    }





}
