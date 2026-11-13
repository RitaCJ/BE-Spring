package com.example.bespring.modulosTest.utilizadoresTest.repository;

import com.example.bespring.modulosTest.utilizadoresTest.domain.Escola;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EscolaRepository extends JpaRepository<Escola, Long> {
    List<Escola> findByAdministrador_idUtilizador(Long idUtilizador);
}
