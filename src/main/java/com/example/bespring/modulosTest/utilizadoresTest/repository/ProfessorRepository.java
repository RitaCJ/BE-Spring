package com.example.bespring.modulosTest.utilizadoresTest.repository;

import com.example.bespring.modulosTest.utilizadoresTest.domain.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
}
