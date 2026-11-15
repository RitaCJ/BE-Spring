package com.example.bespring.modulosTest.utilizadoresTest.controllers;

import com.example.bespring.modulosTest.utilizadoresTest.domain.Utilizador;
import com.example.bespring.modulosTest.utilizadoresTest.repository.UtilizadorRepository;
import com.example.bespring.modulosTest.utilizadoresTest.services.UtilizadorProfissionalService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/utilizadorProfissional")
public class UtilizadorProfissionalController {

    private final UtilizadorProfissionalService utilizadorProfissionalService;
    private final UtilizadorRepository utilizadorRepository;

    public UtilizadorProfissionalController(UtilizadorProfissionalService utilizadorProfissionalService, UtilizadorRepository utilizadorRepository) {
        this.utilizadorProfissionalService = utilizadorProfissionalService;
        this.utilizadorRepository = utilizadorRepository;
    }

    @PostMapping("/{idUtilizador}/aprovar")
    public ResponseEntity<?> aprovar(@PathVariable Long idUtilizador,
                                     @AuthenticationPrincipal UserDetails userDetails) {

        utilizadorProfissionalService.aprovar(idUtilizador, userDetails.getUsername());

        return ResponseEntity.ok("Conta aprovada!");

    }
}
