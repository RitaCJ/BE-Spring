package com.example.bespring.modulosTest.utilizadoresTest.services;

import com.example.bespring.modulosTest.utilizadoresTest.domain.Aluno;
import com.example.bespring.modulosTest.utilizadoresTest.domain.Escola;
import com.example.bespring.modulosTest.utilizadoresTest.domain.Professor;
import com.example.bespring.modulosTest.utilizadoresTest.domain.Turma;
import com.example.bespring.modulosTest.utilizadoresTest.domain.enums.Perfil;
import com.example.bespring.modulosTest.utilizadoresTest.dto.AtualizarAlunoRequest;
import com.example.bespring.modulosTest.utilizadoresTest.dto.CriarAlunoRequest;
import com.example.bespring.modulosTest.utilizadoresTest.repository.AlunoRepository;
import com.example.bespring.modulosTest.utilizadoresTest.repository.EscolaRepository;
import com.example.bespring.modulosTest.utilizadoresTest.repository.ProfessorRepository;
import com.example.bespring.modulosTest.utilizadoresTest.repository.UtilizadorRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

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

        System.out.println("Hereeeeeeee Recebeu" + alunoEntity.isPossuiDaltonismo());

        return alunoRepository.save(alunoEntity);

    }

    public List<Aluno> listarAlunos(Long idProfLogado){

//        var professorExiste = alunoRepository.findById(idProfLogado);
//
//        if(professorExiste.isPresent()){
//            var professor = professorExiste.get();
//
//            if(professor.getProfessor().getIdUtilizador().equals(idProfLogado)){
//                throw new RuntimeException("Nao deve listar alunos");
//            }
//
//            return alunoRepository.findAll();
//        }else{
//            throw new RuntimeException("Professor não encontrado com o id " + idProfLogado);
//        }

        return alunoRepository.findByProfessor_idUtilizador(idProfLogado);

    }

    public Aluno procurarAlunoPorId(Long idAluno, Long idProfLogado){

        var alunoExistente = alunoRepository.findById(idAluno);

        if(alunoExistente.isPresent()){
            var aluno = alunoExistente.get();
                if(aluno.getProfessor().getIdUtilizador().equals(idProfLogado)){
                    return aluno;
                }else{
                    throw new RuntimeException("Não pode procurar por este aluno");
                }

        }

        return null;

    }

    public void atualizarAluno(Long idAluno, AtualizarAlunoRequest atualizarAlunoRequest, Long idProfLogado){

        var alunoExiste = alunoRepository.findById(idAluno);

        if(alunoExiste.isPresent()){
            var aluno = alunoExiste.get();

            if(!aluno.getProfessor().getIdUtilizador().equals(idProfLogado)){
                throw new RuntimeException("Nao pode atualizar este aluno");
            }

            if(atualizarAlunoRequest.primeiroNome() != null ){
                aluno.setPrimeiroNome(atualizarAlunoRequest.primeiroNome());
            }

            if(atualizarAlunoRequest.sobrenome() != null ){
                aluno.setSobrenome(atualizarAlunoRequest.sobrenome());
            }

            if(atualizarAlunoRequest.nomeUtilizador() != null ){
                aluno.setNomeUtilizador(atualizarAlunoRequest.nomeUtilizador());
            }

            if(atualizarAlunoRequest.numeroAluno() != null){
                aluno.setNumeroAluno(atualizarAlunoRequest.numeroAluno());
            }

            if(atualizarAlunoRequest.sala() != null ){
                aluno.setSala(atualizarAlunoRequest.sala());
            }

            if(atualizarAlunoRequest.corFavorita() != null ){
                aluno.setCorFavorita(atualizarAlunoRequest.corFavorita());
            }

            if(atualizarAlunoRequest.possuiDaltonismo() != null){
                aluno.setPossuiDaltonismo(atualizarAlunoRequest.possuiDaltonismo());
            }

            if(atualizarAlunoRequest.genero() != null ){
                aluno.setGenero(atualizarAlunoRequest.genero());
            }

            if(atualizarAlunoRequest.senha() != null ){
                aluno.setSenha(atualizarAlunoRequest.senha());
            }

            alunoRepository.save(aluno);
        }

    }

    public void apagarAluno(Long idAluno, Long idProfLogado){

        var alunoExiste = alunoRepository.findById(idAluno);

        if(alunoExiste.isPresent()){
            var aluno = alunoExiste.get();
            if(aluno.getProfessor().getIdUtilizador().equals(idProfLogado)){
                alunoRepository.deleteById(idAluno);
            }else{
                throw new RuntimeException("Não pode eliminar este aluno");
            }

        }

    }

}
