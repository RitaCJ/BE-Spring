package com.example.bespring.repository;

import com.example.bespring.domain.Escola;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EscolaRepository extends JpaRepository<Escola, Long> {
}
