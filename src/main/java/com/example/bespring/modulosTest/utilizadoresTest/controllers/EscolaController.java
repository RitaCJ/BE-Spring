package com.example.bespring.modulosTest.utilizadoresTest.controllers;

import com.example.bespring.modulosTest.utilizadoresTest.domain.Escola;
import com.example.bespring.modulosTest.utilizadoresTest.domain.Utilizador;
import com.example.bespring.modulosTest.utilizadoresTest.dto.AtualizarEscolaRequest;
import com.example.bespring.modulosTest.utilizadoresTest.dto.CriarEscolaRequest;
import com.example.bespring.modulosTest.utilizadoresTest.dto.CriarEscolaResponse;
import com.example.bespring.modulosTest.utilizadoresTest.services.EscolaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/escolas")
public class EscolaController {


    private final EscolaService escolaService;

    public EscolaController(EscolaService escolaService) {
        this.escolaService = escolaService;
    }

    @PostMapping
    public ResponseEntity<CriarEscolaResponse> registarEscola(@RequestBody @Valid CriarEscolaRequest criarEscolaRequest,
                                                              Authentication authentication) {

        Utilizador admin = (Utilizador) authentication.getPrincipal();

        long idAdminLogado = admin.getIdUtilizador();

        System.out.println("Utilizador logado: " + idAdminLogado);

        Escola escola = escolaService.registarEscola(criarEscolaRequest, idAdminLogado);

        CriarEscolaResponse escolaResponse = new CriarEscolaResponse(
                escola.getIdEscola(),
                escola.getNome(),
                escola.getEndereco(),
                escola.getTelefone(),
                escola.getEmail(),
                escola.getAdministrador().getIdUtilizador()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(escolaResponse);
    }

    @GetMapping
    public ResponseEntity<List<CriarEscolaResponse>> listarEscolas(Authentication authentication) {

        Utilizador admin = (Utilizador) authentication.getPrincipal();

        long idAdminLogado = admin.getIdUtilizador();

        System.out.println("Utilizador logado: " + idAdminLogado);

        List<Escola> escolas = escolaService.listarEscolas(idAdminLogado);

        List<CriarEscolaResponse> escolaResponses = escolas.stream()
                .map( escola -> new CriarEscolaResponse(
                        escola.getIdEscola(),
                        escola.getNome(),
                        escola.getEndereco(),
                        escola.getEmail(),
                        escola.getTelefone(),
                        escola.getAdministrador().getIdUtilizador()
                )).toList();


        return ResponseEntity.status(HttpStatus.OK).body(escolaResponses);
    }

    @GetMapping("/{idEscola}")
    public ResponseEntity<CriarEscolaResponse> buscarEscolaPorId(@PathVariable Long idEscola,
                                                                 Authentication authentication) {
        Utilizador admin = (Utilizador) authentication.getPrincipal();
        long idAdminLogado = admin.getIdUtilizador();

        System.out.println("Utilizador logado: " + idAdminLogado);

        Escola escola = escolaService.procurarEscola(idEscola, idAdminLogado);

        CriarEscolaResponse escolaResponse = new CriarEscolaResponse(
                escola.getIdEscola(),
                escola.getNome(),
                escola.getEndereco(),
                escola.getEmail(),
                escola.getTelefone(),
                escola.getAdministrador().getIdUtilizador()
        );

        return ResponseEntity.status(HttpStatus.OK).body(escolaResponse);
    }

    @PutMapping("/{idEscola}")
    public ResponseEntity<Void> atualizarEscola(@PathVariable Long idEscola,
                                                @RequestBody AtualizarEscolaRequest escolaRequest,
                                                Authentication authentication) {

        Utilizador admin = (Utilizador) authentication.getPrincipal();
        assert admin != null;
        long idAdminLogado = admin.getIdUtilizador();

        escolaService.atualizarEscola(idEscola, escolaRequest, idAdminLogado);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @DeleteMapping("/{idEscola}")
    public ResponseEntity<Void> apagarAluno(@PathVariable Long idEscola,
                                            Authentication authentication){
        Utilizador admin = (Utilizador) authentication.getPrincipal();
        assert admin != null;
        long idAdminLogado = admin.getIdUtilizador();

        escolaService.apagarEscola(idEscola, idAdminLogado);

        return ResponseEntity.noContent().build();
    }
}

