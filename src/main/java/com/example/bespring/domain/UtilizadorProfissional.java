package com.example.bespring.domain;

import com.example.bespring.domain.enums.Genero;
import com.example.bespring.domain.enums.Perfil;
import com.example.bespring.domain.enums.TipoProfissional;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@PrimaryKeyJoinColumn(name = "idUtilizador")
public class UtilizadorProfissional extends Utilizador{

    @NotEmpty
    @Column(length = 12, nullable = false)
    private String telefone;

    @NotEmpty
    @Column(length = 25, nullable = false)
    private String email;

    @NotEmpty
    @Column(length = 15, nullable = false)
    @Size(min = 8, max = 30)
    private String senha;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoProfissional tipo;

    public UtilizadorProfissional(){

    }

    public UtilizadorProfissional(String primeiroNome, String sobrenome, String telefone, Genero genero, String email, String senha, TipoProfissional tipo, Perfil perfil, Escola escola){

        this.senha = senha;

        if(primeiroNome == null || primeiroNome.isBlank()){
            throw new IllegalArgumentException("O campo primeiro nome não pode estar vazio.");
        }else if(sobrenome == null || sobrenome.isBlank()){
            throw new IllegalArgumentException("O campo sobrenome não pode estar vazio.");
        }else if(telefone == null || telefone.isBlank()){
            throw new IllegalArgumentException("O campo telefone não pode estar vazio.");
        }else if(email == null || email.isBlank()){
            throw new IllegalArgumentException("O campo email não pode estar vazio.");
        }else if(senha == null || senha.isBlank()){
            throw new IllegalArgumentException("O campo senha não pode estar vazio.");
        }else if(senha.length() < 8){
            throw new IllegalArgumentException("A senha é muito curta.");
        }else if(senha.length() > 30){
            throw new IllegalArgumentException("A senha é muito longa.");
        }else if(!temMaiuscula()){
            throw new IllegalArgumentException("A senha não tem letra maíuscula.");
        }else if(!temMinuscula()){
            throw new IllegalArgumentException("A senha não tem letra minuscula.");
        }else if(!temNumero()){
            throw new IllegalArgumentException("A senha não tem numero.");
        }else if(!temCaracterEspecial()){
            throw new IllegalArgumentException("A senha não tem caractere especial.");
        }

        this.setPrimeiroNome(primeiroNome);
        this.setSobrenome(sobrenome);
        this.telefone = telefone;
        this.setGenero(genero);
        this.email = email;
        this.tipo = tipo;
        this.setPerfil(perfil);
        this.setEscola(escola);
    }

    private boolean temMaiuscula(){

        for(char c : this.senha.toCharArray()){
            if(Character.isUpperCase(c)){
                return true;
            }
        }

        return false;
    }

    private boolean temMinuscula(){

        for(char c : senha.toCharArray()){
            if(Character.isLowerCase(c)){
                return true;
            }
        }
        return false;
    }

    private boolean temNumero(){

        for(char c : senha.toCharArray()){
            if(Character.isDigit(c)){
                return true;
            }
        }
        return false;
    }

    private boolean temCaracterEspecial(){
        String especiais = "@#$%!";
        for(char c : senha.toCharArray()){
            if(especiais.indexOf(c) >= 0){
                return true;
            }
        }
        return false;
    }
}
