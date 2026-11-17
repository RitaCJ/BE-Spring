package com.example.bespring.modulosTest.utilizadoresTest.controllers;

import com.example.bespring.modulosTest.utilizadoresTest.domain.Psicologo;
import com.example.bespring.modulosTest.utilizadoresTest.dto.AtualizarPsicologoRequest;
import com.example.bespring.modulosTest.utilizadoresTest.dto.CriarPsicologoRequest;
import com.example.bespring.modulosTest.utilizadoresTest.dto.CriarPsicologoResponse;
import com.example.bespring.modulosTest.utilizadoresTest.services.PsicologoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/psicologos")
public class PsicologoController {

    private final PsicologoService psicologoService;

    public PsicologoController(PsicologoService psicologoService) {
        this.psicologoService = psicologoService;
    }

    @PostMapping
    public ResponseEntity<CriarPsicologoResponse> cadastrarPsicologo(@RequestBody @Valid CriarPsicologoRequest psicologoRequest){

        Psicologo psicologo = psicologoService.cadastrarPsicologo(psicologoRequest);

        CriarPsicologoResponse psicologoResponse = new CriarPsicologoResponse(
                psicologo.getIdUtilizador(),
                psicologo.getNumeroIdentificacao(),
                psicologo.getPrimeiroNome(),
                psicologo.getSobrenome(),
                psicologo.getTelefone(),
                psicologo.getGenero(),
                psicologo.getDataNascimento(),
                psicologo.getEmail(),
                psicologo.getTipo(),
                psicologo.getPerfil(),
                psicologo.getEscola().getIdEscola(),
                psicologo.getEstadoConta(),
                psicologo.getCriadoPor(),
                psicologo.getAprovadoPor()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(psicologoResponse);

    }

    @GetMapping("/{idUtilizador}")
    public ResponseEntity<CriarPsicologoResponse> buscarPsicologo(@PathVariable Long idUtilizador,
                                                                  Authentication authentication){

        Authentication auth = authentication;

        String userLogado = auth.getName();

        Psicologo psicologo = psicologoService.procurarPsicologoPeloId(idUtilizador, userLogado);

        CriarPsicologoResponse psicologoResponse = new CriarPsicologoResponse(
                psicologo.getIdUtilizador(),
                psicologo.getNumeroIdentificacao(),
                psicologo.getPrimeiroNome(),
                psicologo.getSobrenome(),
                psicologo.getTelefone(),
                psicologo.getGenero(),
                psicologo.getDataNascimento(),
                psicologo.getEmail(),
                psicologo.getTipo(),
                psicologo.getPerfil(),
                psicologo.getEscola().getIdEscola(),
                psicologo.getEstadoConta(),
                psicologo.getCriadoPor(),
                psicologo.getAprovadoPor()
        );

        return ResponseEntity.status(HttpStatus.OK).body(psicologoResponse);

    }

    @PutMapping("/{idUtilizador}")
    public ResponseEntity<Void> atualizarPsicologo(@PathVariable Long idUtilizador,
                                                   @RequestBody AtualizarPsicologoRequest psicologoRequest,
                                                   Authentication authentication) {
        System.out.println("Authentication " + authentication);

        //Pegar o psicologo que se encontra autenticado.
        String userLogado = authentication.getName();

        psicologoService.atualizarPsicologo(idUtilizador, psicologoRequest, userLogado);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{idUtilizador}")
    public ResponseEntity<Void> deletarProfessor(@PathVariable Long idUtilizador, Authentication authentication) {

        String userLogado = authentication.getName();

        psicologoService.apagarPsicologo(idUtilizador, userLogado);

        return ResponseEntity.noContent().build();

    }
}
