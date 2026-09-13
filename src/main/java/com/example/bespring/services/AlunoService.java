package com.example.bespring.services;

import com.example.bespring.domain.Aluno;
import com.example.bespring.domain.Escola;
import com.example.bespring.domain.Professor;
import com.example.bespring.domain.Turma;
import com.example.bespring.domain.enums.Perfil;
import com.example.bespring.dto.CriarAlunoRequest;
import com.example.bespring.repository.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;
    private final EscolaRepository escolaRepository;
    private final ProfessorRepository professorRepository;
    private final UtilizadorRepository utilizadorRepository;


    public AlunoService(AlunoRepository alunoRepository,EscolaRepository escolaRepository,
                        ProfessorRepository professorRepository, UtilizadorRepository utilizadorRepository ) {
        this.alunoRepository = alunoRepository;
        this.escolaRepository = escolaRepository;
        this.professorRepository = professorRepository;
        this.utilizadorRepository = utilizadorRepository;
    }


    public Aluno registarAluno(CriarAlunoRequest criarAlunoRequest, Long idProfessor) {

        if(utilizadorRepository.findByLogin(criarAlunoRequest.nomeUtilizador()) != null){
            throw new RuntimeException("Já existe um utilizador com esse nome de utilizador");
        }

        Escola escola = escolaRepository.findById(criarAlunoRequest.idEscola())
                .orElseThrow(() -> new RuntimeException("Escola não encontrado"));

        Professor professor = professorRepository.findById(idProfessor)
                .orElseThrow( () -> new RuntimeException("Professor não encontrado"));

        String encryptPassword = new BCryptPasswordEncoder().encode(criarAlunoRequest.senha());

        Perfil perfil = Perfil.ALUNO;

        Turma turma = null;

        Aluno alunoEntity = new Aluno(
                criarAlunoRequest.primeiroNome(),
                criarAlunoRequest.sobrenome(),
                criarAlunoRequest.nomeUtilizador(),
                criarAlunoRequest.numeroAluno(),
                criarAlunoRequest.sala(),
                criarAlunoRequest.corFavorita(),
                criarAlunoRequest.possuiDaltonismo(),
                criarAlunoRequest.genero(),
                escola,
                perfil,
                encryptPassword,
                turma,
                professor
        );

        System.out.println("Hereeeeeeee Resebeu" + alunoEntity.isPossuiDaltonismo());

        return alunoRepository.save(alunoEntity);

    }

}
