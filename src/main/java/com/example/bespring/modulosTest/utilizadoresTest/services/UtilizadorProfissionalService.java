package com.example.bespring.modulosTest.utilizadoresTest.services;

import com.example.bespring.modulosTest.utilizadoresTest.domain.Utilizador;
import com.example.bespring.modulosTest.utilizadoresTest.domain.UtilizadorProfissional;
import com.example.bespring.modulosTest.utilizadoresTest.domain.enums.EstadoConta;
import com.example.bespring.modulosTest.utilizadoresTest.repository.EscolaRepository;
import com.example.bespring.modulosTest.utilizadoresTest.repository.ProfessorRepository;
import com.example.bespring.modulosTest.utilizadoresTest.repository.UtilizadorProfissionalRepository;
import com.example.bespring.modulosTest.utilizadoresTest.repository.UtilizadorRepository;
import jakarta.annotation.Resource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.util.NoSuchElementException;

@Service
public class UtilizadorProfissionalService {

    private final EscolaRepository escolaRepository;
    private final UtilizadorRepository utilizadorRepository;
    private final ProfessorRepository professorRepository;
    private final UtilizadorProfissionalRepository utilizadorProfissionalRepository;

    public UtilizadorProfissionalService(EscolaRepository escolaRepository, UtilizadorRepository utilizadorRepository, ProfessorRepository professorRepository, UtilizadorProfissionalRepository utilizadorProfissionalRepository) {
        this.escolaRepository = escolaRepository;
        this.utilizadorRepository = utilizadorRepository;
        this.professorRepository = professorRepository;
        this.utilizadorProfissionalRepository = utilizadorProfissionalRepository;
    }


    public UtilizadorProfissional aprovar(Long idUser, String login){


        UserDetails userDetails = utilizadorRepository.findByLogin(login);

        if(userDetails == null){
            throw new RuntimeException("Admin escola nao encontrado");
        }

        Utilizador adminEscola = (Utilizador) userDetails;

        UtilizadorProfissional profissional = utilizadorProfissionalRepository
                .findById(idUser)
                .orElseThrow(() -> new RuntimeException("Utilizador nao encontrado"));

        //Verificar o estado da conta
        if(profissional.getEstadoConta() != EstadoConta.PENDENTE){
            throw new IllegalStateException("Conta nao esta pendente");
        }

        //Aprovar profissional
        profissional.setEstadoConta(EstadoConta.ATIVA);
        profissional.setAprovadoPor(adminEscola);

        return utilizadorRepository.save(profissional);
    }


}
