package com.example.bespring.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity //Habilitar a configuração do web Security
public class SecurityConfiguration {

    private final SecurityFilter securityFilter;

    public SecurityConfiguration(SecurityFilter securityFilter) {
        this.securityFilter = securityFilter;
    }

    //Corrente de filtro de segurança.
    //Aplicação das requisições para fazer a segurança.
    //Filtros para fazer as validações do utilizadores.
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/professores").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/psicologos").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/api/professores/{idUtilizador}").hasRole("PROFESSOR")
                        .requestMatchers(HttpMethod.GET, "/api/professores/{idUtilizador}").hasRole("PROFESSOR")

                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        //Faz a criptografia das senhas.
        //Utiliza um algoritmo de hash conhecido e faz o hash dos dados. Transformar os dado (senhas) em dados criptografados.
        return new BCryptPasswordEncoder();
    }

}
