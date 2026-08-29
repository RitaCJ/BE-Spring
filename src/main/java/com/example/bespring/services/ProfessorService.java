package com.example.bespring.services;

import com.example.bespring.domain.Professor;
import com.example.bespring.dto.CriarProfessorRequest;
import com.example.bespring.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    public ProfessorService(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    public Professor cadastrarProfessor(CriarProfessorRequest criarProfessorRequest){

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
                criarProfessorRequest.escola()
        );

      return professorRepository.save(entityProfessor);

    }

}
