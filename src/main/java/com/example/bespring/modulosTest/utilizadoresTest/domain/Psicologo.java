package com.example.bespring.modulosTest.utilizadoresTest.domain;

import com.example.bespring.modulosTest.utilizadoresTest.domain.enums.EstadoConta;
import com.example.bespring.modulosTest.utilizadoresTest.domain.enums.Genero;
import com.example.bespring.modulosTest.utilizadoresTest.domain.enums.Perfil;
import com.example.bespring.modulosTest.utilizadoresTest.domain.enums.TipoProfissional;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@PrimaryKeyJoinColumn(name = "idUtilizador")
public class Psicologo extends UtilizadorProfissional{

    public Psicologo(){

    }

    public Psicologo(String numeroIdentificacao, String primeiroNome, String sobrenome, String telefone, Genero genero, LocalDate dataNascimento,
                     String email, String senha, TipoProfissional tipo, Perfil perfil, Escola escola, EstadoConta estadoConta, Utilizador criadoPor, Utilizador aprovadoPor) {

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

        this.setNumeroIdentificacao(numeroIdentificacao);
        this.setPrimeiroNome(primeiroNome);
        this.setSobrenome(sobrenome);
        this.setTelefone(telefone);
        this.setGenero(genero);
        this.setDataNascimento(dataNascimento);
        this.setEmail(email);
        this.setSenha(senha);
        this.setLogin(email);
        this.setTipo(tipo);
        this.setPerfil(Perfil.PSICOLOGO);
        this.setEscola(escola);
        this.setEstadoConta(EstadoConta.PENDENTE);
        this.setCriadoPor(criadoPor);
        this.setAprovadoPor(aprovadoPor);
    }
}
