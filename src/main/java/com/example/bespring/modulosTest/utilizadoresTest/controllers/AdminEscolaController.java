package com.example.bespring.modulosTest.utilizadoresTest.controllers;

import com.example.bespring.modulosTest.utilizadoresTest.domain.Utilizador;
import com.example.bespring.modulosTest.utilizadoresTest.dto.*;
import com.example.bespring.modulosTest.utilizadoresTest.services.AdminEscolaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;

@RestController
@RequestMapping("/api/adminEscola")
public class AdminEscolaController {

    private final AdminEscolaService adminEscolaService;

    public AdminEscolaController(AdminEscolaService adminEscolaService) {
        this.adminEscolaService = adminEscolaService;
    }


    @PostMapping
    public ResponseEntity<CriarAdminEscolaResponse> registarAdminEscola(@RequestBody @Valid CriarAdminEscolaRequest criarAdminEscolaRequest,
                                                                   Authentication authentication) throws AccessDeniedException {

        Utilizador user = (Utilizador) authentication.getPrincipal();

        long idAdminLogado;

        if(user != null) {
            idAdminLogado = user.getIdUtilizador();
        }else{
            throw new AccessDeniedException("Utilizador nao autenticado");
        }

        Utilizador adminEscola = adminEscolaService.registarAdminEscola(criarAdminEscolaRequest, idAdminLogado);

        CriarAdminEscolaResponse response = new CriarAdminEscolaResponse(
           adminEscola.getIdUtilizador(),
           adminEscola.getPrimeiroNome(),
           adminEscola.getSobrenome(),
           adminEscola.getGenero(),
           adminEscola.getPerfil(),
           adminEscola.getDataNascimento(),
           adminEscola.getEscola().getIdEscola(),
           adminEscola.getEstadoConta()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{idUtilizador}")
    public ResponseEntity<CriarAdminEscolaResponse> buscarAdminEscolaPorId(@PathVariable Long idUtilizador,
                                                                 Authentication authentication) throws AccessDeniedException {
        Utilizador admin = (Utilizador) authentication.getPrincipal();

        long idAdminLogado;

        if(admin != null) {
            idAdminLogado = admin.getIdUtilizador();
        }else{
            throw new AccessDeniedException("Utilizador nao autenticado");
        }

        Utilizador adminEscola = adminEscolaService.procurarAdminEscola(idUtilizador, idAdminLogado);

        CriarAdminEscolaResponse adminEscolaResponse = new CriarAdminEscolaResponse(
                adminEscola.getIdUtilizador(),
                adminEscola.getPrimeiroNome(),
                adminEscola.getSobrenome(),
                adminEscola.getGenero(),
                adminEscola.getPerfil(),
                adminEscola.getDataNascimento(),
                adminEscola.getEscola().getIdEscola(),
                adminEscola.getEstadoConta()
        );

        return ResponseEntity.status(HttpStatus.OK).body(adminEscolaResponse);
    }

    @PutMapping("/{idUtilizador}")
    public ResponseEntity<Void> atualizarAdminEscola(@PathVariable Long idUtilizador,
                                                @RequestBody AtualizarAdminEscolaRequest adminEscolaRequest,
                                                Authentication authentication) throws AccessDeniedException {

        Utilizador admin = (Utilizador) authentication.getPrincipal();

        long idAdminLogado;

        if(admin != null) {
            idAdminLogado = admin.getIdUtilizador();
        }else{
            throw new AccessDeniedException("Utilizador nao autenticado");
        }

        adminEscolaService.atualizarAdminEscola(idUtilizador, adminEscolaRequest, idAdminLogado);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @DeleteMapping("/{idUtilizador}")
    public ResponseEntity<Void> apagarAdminEscola(@PathVariable Long idUtilizador,
                                            Authentication authentication) throws AccessDeniedException {
        Utilizador admin = (Utilizador) authentication.getPrincipal();

        long idAdminLogado;

        if(admin != null) {
            idAdminLogado = admin.getIdUtilizador();
        }else{
            throw new AccessDeniedException("Utilizador nao autenticado");
        }

        adminEscolaService.apagarAdminEscola(idUtilizador, idAdminLogado);

        return ResponseEntity.noContent().build();
    }

}
