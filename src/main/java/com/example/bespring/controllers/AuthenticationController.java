package com.example.bespring.controllers;

import com.example.bespring.domain.Utilizador;
import com.example.bespring.dto.LoginRequest;
import com.example.bespring.dto.LoginResponse;
import com.example.bespring.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public AuthenticationController(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid LoginRequest loginRequest) {

        var usernamePassword = new UsernamePasswordAuthenticationToken(loginRequest.login(), loginRequest.senha());
        //usernamePassword -> Username e Password, juntos formados como um token.
        var auth = this.authenticationManager.authenticate(usernamePassword);
        //Gerar um token
        var token = tokenService.generateToken((Utilizador) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponse(token));

    }
}
