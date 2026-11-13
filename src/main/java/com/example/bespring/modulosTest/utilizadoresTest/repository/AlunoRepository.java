package com.example.bespring.modulosTest.utilizadoresTest.repository;

import com.example.bespring.modulosTest.utilizadoresTest.domain.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    List<Aluno> findByProfessor_idUtilizador(Long idProfLogado);
}
