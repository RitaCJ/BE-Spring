package com.example.bespring.domain;

import com.example.bespring.domain.enums.Genero;
import com.example.bespring.domain.enums.Perfil;
import com.example.bespring.domain.enums.TipoProfissional;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@PrimaryKeyJoinColumn(name = "idUtilizador")
public class Professor extends UtilizadorProfissional{

    public Professor() {

    }

    public Professor(String primeiroNome, String sobrenome, String telefone, Genero genero, String email, String senha, TipoProfissional tipo, Perfil perfil, Escola escola){

        if(primeiroNome == null || primeiroNome.isBlank()){
            throw new IllegalArgumentException("O campo primeiro nome não pode estar vazio.");
        }else if(sobrenome == null || sobrenome.isBlank()){
            throw new IllegalArgumentException("O campo sobrenome não pode estar vazio.");
        }else if(telefone == null || telefone.isBlank()){
            throw new IllegalArgumentException("O campo telefone não pode estar vazio.");
        }else if(email == null || email.isBlank()){
            throw new IllegalArgumentException("O campo email não pode estar vazio.");
        }else if(senha == null || senha.isBlank()) {
            throw new IllegalArgumentException("O campo senha não pode estar vazio.");
        }

        this.setPrimeiroNome(primeiroNome);
        this.setSobrenome(sobrenome);
        this.setTelefone(telefone);
        this.setGenero(genero);
        this.setEmail(email);
        this.setSenha(senha);
        this.setTipo(tipo);
        this.setPerfil(perfil);
        this.setEscola(escola);
    }
}
