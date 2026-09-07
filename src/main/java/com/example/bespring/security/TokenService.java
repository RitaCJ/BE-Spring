package com.example.bespring.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.bespring.domain.Utilizador;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    //Variavel de ambiente.
    @Value("${api.security.token.secret}")
    private String secret;

    //Metodo para gerar o token
    public String generateToken(Utilizador utilizador) {
        try{
            //algoritmo de geração de token
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("sistema-rastreio-discalculia-api") //Emissor do token
                    .withSubject(utilizador.getLogin())//O utilizador que recebe o token
                    .withExpiresAt(generateExpirationDate())
                    .sign(algorithm); //Assinatura e geração final.
            return token;
        }catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao gerar token", exception);
        }
    }

    //Metodo para validar o token
    public String validateToken(String token) {
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("sistema-rastreio-discalculia-api")
                    .build()
                    .verify(token)
                    .getSubject();

        }catch (JWTVerificationException exception) {
            return "";
        }
    }

    public Instant generateExpirationDate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("+01:00"));
    }

}
