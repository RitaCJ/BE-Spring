package com.example.bespring.modulosTest.utilizadoresTest.controllers;

import com.example.bespring.modulosTest.utilizadoresTest.dto.LoginRequest;
import com.example.bespring.modulosTest.utilizadoresTest.services.AutenticacaoService;
import com.example.bespring.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final AutenticacaoService authenticationService;

    public AuthenticationController(AuthenticationManager authenticationManager, TokenService tokenService, AutenticacaoService authenticationService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid LoginRequest loginRequest) {

//        var usernamePassword = new UsernamePasswordAuthenticationToken(loginRequest.login(), loginRequest.senha());
//        //usernamePassword -> Username e Password, juntos formados como um token.
//        var auth = this.authenticationManager.authenticate(usernamePassword);
//
//        //Gerar um token
//        var token = tokenService.generateToken((Utilizador) auth.getPrincipal());
//
//        return ResponseEntity.ok(new LoginResponse(token));

        String token = authenticationService.autenticar(loginRequest);

        return ResponseEntity.ok(token);

    }
}
