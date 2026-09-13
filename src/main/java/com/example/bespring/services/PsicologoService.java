package com.example.bespring.services;

import com.example.bespring.domain.Escola;
import com.example.bespring.domain.Psicologo;
import com.example.bespring.domain.enums.Perfil;
import com.example.bespring.dto.CriarPsicologoRequest;
import com.example.bespring.repository.EscolaRepository;
import com.example.bespring.repository.PsicologoRepository;
import com.example.bespring.repository.UtilizadorRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

        //Converter de DTO para Entity
        Psicologo psicologoEntity = new Psicologo(
                psicologoRequest.primeiroNome(),
                psicologoRequest.sobrenome(),
                psicologoRequest.telefone(),
                psicologoRequest.genero(),
                psicologoRequest.email(),
                passwordEncrypto,
                psicologoRequest.tipo(),
                perfil,
                escola
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

}
