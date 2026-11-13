package com.example.bespring.modulosTest.utilizadoresTest.services;

import com.example.bespring.modulosTest.utilizadoresTest.domain.Escola;
import com.example.bespring.modulosTest.utilizadoresTest.domain.Professor;
import com.example.bespring.modulosTest.utilizadoresTest.domain.enums.Perfil;
import com.example.bespring.modulosTest.utilizadoresTest.dto.AtualizarProfessorRequest;
import com.example.bespring.modulosTest.utilizadoresTest.dto.CriarProfessorRequest;
import com.example.bespring.modulosTest.utilizadoresTest.repository.EscolaRepository;
import com.example.bespring.modulosTest.utilizadoresTest.repository.ProfessorRepository;
import com.example.bespring.modulosTest.utilizadoresTest.repository.UtilizadorRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

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
                .orElseThrow(() -> new RuntimeException("Escola não encontrada"));

        String encryptoPassword = new BCryptPasswordEncoder().encode(criarProfessorRequest.senha());

        Perfil perfil = Perfil.PROFESSOR;

        //Converte DTO para ENTITY
        Professor  entityProfessor = new Professor(
                criarProfessorRequest.primeiroNome(),
                criarProfessorRequest.sobrenome(),
                criarProfessorRequest.telefone(),
                criarProfessorRequest.genero(),
                criarProfessorRequest.email(),
                encryptoPassword,
                criarProfessorRequest.tipo(),
                perfil,
                escola
        );

      return professorRepository.save(entityProfessor);

    }

    public Professor procurarProfessorPorId(Long id, String utilizadorLogado){

        var professorExistetente = professorRepository.findById(id);

        if(professorExistetente.isPresent()){
            var professor1 = professorExistetente.get();
            if(!professor1.getEmail().equals(utilizadorLogado)){
                throw new RuntimeException("Não pode procurar por este utilizador");
            }

            return professor1;

        }else{

            throw new RuntimeException("Professor não encontrado com o id " + id);
        }

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
                throw new RuntimeException("Nao pode atualizar este professor");
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

    public void apagarProfessor(Long id, String utilizadorLogado){

//        var professorExiste = professorRepository.existsById(id);

        var professorExiste = professorRepository.findById(id);

        if(professorExiste.isPresent()){
            if(professorExiste.get().getEmail().equals(utilizadorLogado)){
                professorRepository.deleteById(id);

            }else{
                throw new RuntimeException("Não pode eliminar este professor");
            }


        }
    }

}
