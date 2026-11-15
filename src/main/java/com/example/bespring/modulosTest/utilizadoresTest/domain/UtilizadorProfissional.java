package com.example.bespring.modulosTest.utilizadoresTest.domain;

import com.example.bespring.modulosTest.utilizadoresTest.domain.enums.EstadoConta;
import com.example.bespring.modulosTest.utilizadoresTest.domain.enums.Genero;
import com.example.bespring.modulosTest.utilizadoresTest.domain.enums.Perfil;
import com.example.bespring.modulosTest.utilizadoresTest.domain.enums.TipoProfissional;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

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

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoProfissional tipo;

    @NotEmpty
    @Column(length = 20, nullable = false)
    private String numeroIdentificacao;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "aprovado_por", nullable = true)
    private Utilizador aprovadoPor;


    public UtilizadorProfissional(){

    }

    public UtilizadorProfissional(String numeroIdentificacao, String primeiroNome, String sobrenome, String telefone, Genero genero, LocalDate dataNascimento,
                                  String email, String senha, TipoProfissional tipo, Perfil perfil, Escola escola, EstadoConta estadoConta, Utilizador criadoPor, Utilizador aprovadoPor) {

        this.setSenha(senha);

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
            throw new IllegalArgumentException("A senha não tem letra maiuscula.");
        }else if(!temMinuscula()){
            throw new IllegalArgumentException("A senha não tem letra minuscula.");
        }else if(!temNumero()){
            throw new IllegalArgumentException("A senha não tem numero.");
        }else if(!temCaracterEspecial()){
            throw new IllegalArgumentException("A senha não tem caractere especial.");
        }else if(!telefone.matches("[0-9]{9}$")){
            throw new IllegalArgumentException("O telefone deve conter: 9 dígitos e não pode ter letras");
        }else if(!numeroIdentificacao.matches("^[a-zA-Z0-9]{1,20}$")){
            throw new IllegalArgumentException("O numero deve conter: 1 e 20");
        }

        this.numeroIdentificacao = numeroIdentificacao;
        this.setPrimeiroNome(primeiroNome);
        this.setSobrenome(sobrenome);
        this.telefone = telefone;
        this.setGenero(genero);
        this.setDataNascimento(dataNascimento);
        this.email = email;
        this.tipo = tipo;
        this.setPerfil(perfil);
        this.setLogin(email);
        this.setEscola(escola);
        this.setEstadoConta(estadoConta);
        this.setCriadoPor(criadoPor);
        this.aprovadoPor = aprovadoPor;
    }

    private boolean temMaiuscula(){

        for(char c : getSenha().toCharArray()){
            if(Character.isUpperCase(c)){
                return true;
            }
        }

        return false;
    }

    private boolean temMinuscula(){

        for(char c : this.getSenha().toCharArray()){
            if(Character.isLowerCase(c)){
                return true;
            }
        }
        return false;
    }

    private boolean temNumero(){

        for(char c : getSenha().toCharArray()){
            if(Character.isDigit(c)){
                return true;
            }
        }
        return false;
    }

    private boolean temCaracterEspecial(){
        String especiais = "@#$%!";
        for(char c : this.getSenha().toCharArray()){
            if(especiais.indexOf(c) >= 0){
                return true;
            }
        }
        return false;
    }
}
