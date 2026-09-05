package com.example.bespring.services;

import com.example.bespring.domain.Escola;
import com.example.bespring.domain.Professor;
import com.example.bespring.dto.AtualizarProfessorRequest;
import com.example.bespring.dto.CriarProfessorRequest;
import com.example.bespring.repository.EscolaRepository;
import com.example.bespring.repository.ProfessorRepository;
import com.example.bespring.repository.UtilizadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService {

    private final ProfessorRepository professorRepository;
    private final EscolaRepository escolaRepository;
    private final UtilizadorRepository utilizadorRepository;

    public ProfessorService(ProfessorRepository professorRepository, EscolaRepository escolaRepository, UtilizadorRepository utilizadorRepository) {

        this.professorRepository = professorRepository;
        this.escolaRepository = escolaRepository;
        this.utilizadorRepository = utilizadorRepository;

    }


    public Professor cadastrarProfessor(CriarProfessorRequest criarProfessorRequest) {

        //Verificar se o utilizador já existe no banco de dados.
        if (utilizadorRepository.findByLogin(criarProfessorRequest.email()) != null){
            throw new RuntimeException("Já existe um utilizador com esse e-mail");
        }

        Escola escola = escolaRepository.findById(criarProfessorRequest.idEscola())
                .orElseThrow(() -> new RuntimeException("Escola não encontrado"));

        String encryptoPassword = new BCryptPasswordEncoder().encode(criarProfessorRequest.senha());

        //Converte DTO para ENTITY
        Professor  entityProfessor = new Professor(
                criarProfessorRequest.primeiroNome(),
                criarProfessorRequest.sobrenome(),
                criarProfessorRequest.telefone(),
                criarProfessorRequest.genero(),
                criarProfessorRequest.email(),
                encryptoPassword,
                criarProfessorRequest.tipo(),
                criarProfessorRequest.perfil(),
                escola
        );

      return professorRepository.save(entityProfessor);

    }

    public Professor procurarProfessorPorId(Long id){

        return professorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Professor não encontrado com o id " + id));

    }

    public List<Professor> listarProfessores(){

        return professorRepository.findAll();

    }


    public void atualizarProfessor(Long id, AtualizarProfessorRequest atualizarProfessorRequest, String usernameLogado){

        var professorExiste = professorRepository.findById(id);

        if(professorExiste.isPresent()){
            //Professor encontrado
            var professor = professorExiste.get();

            if(!professor.getEmail().equals(usernameLogado)){
                throw new RuntimeException("Não poder atualizar este professor");
            }

            if(atualizarProfessorRequest.primeiroNome() != null ){
                professor.setPrimeiroNome(atualizarProfessorRequest.primeiroNome());
            }

            if(atualizarProfessorRequest.sobrenome() != null ){
                professor.setSobrenome(atualizarProfessorRequest.sobrenome());
            }

            if(atualizarProfessorRequest.telefone() != null ){
                professor.setTelefone(atualizarProfessorRequest.telefone());
            }

            if(atualizarProfessorRequest.genero() != null ){
                professor.setGenero(atualizarProfessorRequest.genero());
            }

            if(atualizarProfessorRequest.email() != null){
                professor.setEmail(atualizarProfessorRequest.email());
            }

            if(atualizarProfessorRequest.senha() != null ){
                professor.setSenha(atualizarProfessorRequest.senha());
            }

            professorRepository.save(professor);
        }

    }

    public void apagarProfessor(Long id){

        var professorExiste = professorRepository.existsById(id);

        if(professorExiste){
            professorRepository.deleteById(id);
        }
    }


}
