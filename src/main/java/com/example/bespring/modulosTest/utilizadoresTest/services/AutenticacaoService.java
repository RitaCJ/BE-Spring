package com.example.bespring.modulosTest.utilizadoresTest.services;

import com.example.bespring.modulosTest.utilizadoresTest.domain.Utilizador;
import com.example.bespring.modulosTest.utilizadoresTest.domain.enums.EstadoConta;
import com.example.bespring.modulosTest.utilizadoresTest.dto.LoginRequest;
import com.example.bespring.modulosTest.utilizadoresTest.repository.UtilizadorRepository;
import com.example.bespring.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoService {


    private final UtilizadorRepository utilizadorRepository;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;

    public AutenticacaoService(UtilizadorRepository utilizadorRepository, TokenService tokenService,
                               PasswordEncoder passwordEncoder) {
        this.utilizadorRepository = utilizadorRepository;
        this.tokenService = tokenService;
        this.passwordEncoder = passwordEncoder;
    }


    public String autenticar(LoginRequest request) {

        //Procurar utilizador pelo login
        UserDetails userDetails = utilizadorRepository.findByLogin(request.login());

        if(userDetails == null){
            throw new RuntimeException("Login ou Senha invalidos");
        }

        Utilizador utilizador = (Utilizador) userDetails;

        //Verificar senha
        if(!passwordEncoder.matches(request.senha(), utilizador.getSenha())){
            throw new RuntimeException("Login ou Senha incorreta");
        }

        //Verificar o estado da conta
        if(utilizador.getEstadoConta() != EstadoConta.ATIVA){
            throw new RuntimeException("Conta pendente de aprovação");
        }

        return tokenService.generateToken(utilizador);
    }


}
