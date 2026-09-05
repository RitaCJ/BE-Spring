package com.example.bespring.repository;

import com.example.bespring.domain.Utilizador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UtilizadorRepository extends JpaRepository<Utilizador, Long> {
    //Metódo sem implementação.
    UserDetails findByLogin(String login);
}
