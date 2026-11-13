package com.example.bespring.modulosTest.utilizadoresTest.repository;

import com.example.bespring.modulosTest.utilizadoresTest.domain.Turma;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TurmaRepository extends JpaRepository<Turma, Long> {

    boolean existsByNome(String nome);

}
