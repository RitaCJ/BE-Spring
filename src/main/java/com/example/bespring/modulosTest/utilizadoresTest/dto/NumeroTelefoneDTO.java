package com.example.bespring.modulosTest.utilizadoresTest.dto;

public record NumeroTelefoneDTO(String numero) {

    public NumeroTelefoneDTO {
        if(numero == null || !numero.matches("[0-9]{9}")){
            throw new IllegalArgumentException("O número deve conter exatamente 9 dígitos");
        }
    }
}
