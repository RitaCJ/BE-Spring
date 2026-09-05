package com.example.bespring.services;

import com.example.bespring.repository.UtilizadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService implements UserDetailsService {


    private final UtilizadorRepository utilizadorRepository;

    public AuthorizationService(UtilizadorRepository utilizadorRepository) {
        this.utilizadorRepository = utilizadorRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("Usuario recebido: " + username);
        //Consultar os utilizadores no banco de dados.
        //return utilizadorRepository.findByLogin(username);
        UserDetails user = utilizadorRepository.findByLogin(username);

        System.out.println("Usuario encontrado: " + user.getUsername());

        return user;
    }
}
