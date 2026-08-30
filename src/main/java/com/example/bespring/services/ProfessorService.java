package com.example.bespring.services;

import com.example.bespring.domain.Escola;
import com.example.bespring.domain.Professor;
import com.example.bespring.dto.CriarProfessorRequest;
import com.example.bespring.repository.EscolaRepository;
import com.example.bespring.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfessorService {

    private final ProfessorRepository professorRepository;
    private final EscolaRepository escolaRepository;

    public ProfessorService(ProfessorRepository professorRepository, EscolaRepository escolaRepository) {

        this.professorRepository = professorRepository;
        this.escolaRepository = escolaRepository;
    }

    public Professor cadastrarProfessor(CriarProfessorRequest criarProfessorRequest){

        Escola escola = escolaRepository.findById(criarProfessorRequest.idEscola())
                .orElseThrow(() -> new RuntimeException("Escola não encontrado"));

        //Converte DTO para ENTITY
        Professor  entityProfessor = new Professor(
                criarProfessorRequest.primeiroNome(),
                criarProfessorRequest.sobrenome(),
                criarProfessorRequest.telefone(),
                criarProfessorRequest.genero(),
                criarProfessorRequest.email(),
                criarProfessorRequest.senha(),
                criarProfessorRequest.tipo(),
                criarProfessorRequest.perfil(),
                escola
        );

      return professorRepository.save(entityProfessor);

    }

}
