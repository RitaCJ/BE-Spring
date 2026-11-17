package com.example.bespring.modulosTest.utilizadoresTest.services;

import com.example.bespring.modulosTest.utilizadoresTest.domain.Escola;
import com.example.bespring.modulosTest.utilizadoresTest.domain.Psicologo;
import com.example.bespring.modulosTest.utilizadoresTest.domain.enums.EstadoConta;
import com.example.bespring.modulosTest.utilizadoresTest.domain.enums.Perfil;
import com.example.bespring.modulosTest.utilizadoresTest.dto.AtualizarPsicologoRequest;
import com.example.bespring.modulosTest.utilizadoresTest.dto.CriarPsicologoRequest;
import com.example.bespring.modulosTest.utilizadoresTest.repository.EscolaRepository;
import com.example.bespring.modulosTest.utilizadoresTest.repository.PsicologoRepository;
import com.example.bespring.modulosTest.utilizadoresTest.repository.UtilizadorRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PsicologoService {

    private final PsicologoRepository psicologoRepository;
    private final EscolaRepository escolaRepository;
    private final UtilizadorRepository utilizadorRepository;

    public PsicologoService(PsicologoRepository psicologoRepository, EscolaRepository escolaRepository, UtilizadorRepository utilizadorRepository) {
        this.psicologoRepository = psicologoRepository;
        this.escolaRepository = escolaRepository;
        this.utilizadorRepository = utilizadorRepository;
    }

    public Psicologo cadastrarPsicologo(CriarPsicologoRequest psicologoRequest){

        //Verificar se o psicologo já existe.
        if(utilizadorRepository.findByLogin(psicologoRequest.email()) != null){
            throw new RuntimeException("Já existe um utilizador com este email");
        }

        Escola escola = escolaRepository.findById(psicologoRequest.idEscola())
                .orElseThrow(() -> new RuntimeException("Escola não encontrado"));

        //Criptografar a senha
        String passwordEncrypto  = new BCryptPasswordEncoder().encode(psicologoRequest.senha());

        Perfil perfil = Perfil.PSICOLOGO;

        EstadoConta estadoConta = EstadoConta.PENDENTE;

        //Converter de DTO para Entity
        Psicologo psicologoEntity = new Psicologo(
                psicologoRequest.numeroIdentificacao(),
                psicologoRequest.primeiroNome(),
                psicologoRequest.sobrenome(),
                psicologoRequest.telefone(),
                psicologoRequest.genero(),
                psicologoRequest.dataNascimento(),
                psicologoRequest.email(),
                passwordEncrypto,
                psicologoRequest.tipo(),
                perfil,
                escola,
                estadoConta,
                null,
                null
        );

        return psicologoRepository.save(psicologoEntity);

    }

    public Psicologo procurarPsicologoPeloId(Long id, String utilizadorLogado){

        var psicologoExiste = psicologoRepository.findById(id);

        if(psicologoExiste.isPresent()){

            var psicologo1 = psicologoExiste.get();

            if(!psicologo1.getEmail().equals(utilizadorLogado)){
                throw new RuntimeException("Não pode procurar por este utilizador");
            }

            return psicologo1;

        }else{
            throw new RuntimeException("Psicologo não encontrado com o id " + id);
        }
    }

    public void atualizarPsicologo(Long idUser, AtualizarPsicologoRequest psicologoRequest, String userLogado){

        var psicologoExiste = psicologoRepository.findById(idUser);

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        if(psicologoExiste.isPresent()){
            //Psicologo encontrado.
            var psicologo = psicologoExiste.get();

            if(!psicologo.getEmail().equals(userLogado)){
                throw new RuntimeException("Nao pode atualizar este psicólogo");
            }

            if(psicologoRequest.primeiroNome() != null ){
                psicologo.setPrimeiroNome(psicologoRequest.primeiroNome());
            }

            if(psicologoRequest.sobrenome() != null ){
                psicologo.setSobrenome(psicologoRequest.sobrenome());
            }

            if(psicologoRequest.telefone() != null ){
                psicologo.setTelefone(psicologoRequest.telefone());
            }

            if(psicologoRequest.genero() != null ){
                psicologo.setGenero(psicologoRequest.genero());
            }

            if(psicologoRequest.email() != null){
                psicologo.setEmail(psicologoRequest.email());
            }

            if(psicologoRequest.senha() != null ){
                psicologo.setSenha(passwordEncoder.encode(psicologoRequest.senha()));
            }

            psicologoRepository.save(psicologo);
        }

    }

    public void apagarPsicologo(Long id, String utilizadorLogado){

        var psicologoExiste = psicologoRepository.findById(id);

        if(psicologoExiste.isPresent()){

            if(psicologoExiste.get().getEmail().equals(utilizadorLogado)){
                psicologoRepository.deleteById(id);
            }else{
                throw new RuntimeException("Nao pode eliminar este psicologo");
            }
        }
    }

}
