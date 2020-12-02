package com.example.demo.dto;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

public class ClientesDTO {
    
    @NotBlank(message = "Nome obrigatório!")
    @Length(min = 4, max = 40, message = "Nome deve ter no mínimo 4 e no máximo 40 caracteres")
    private String nome;

    @NotBlank(message = "Endereço obrigatório!")
    @Length(min = 10, max = 80, message = "Endereço deve ter no mínimo 10 e no máximo 80 caracteres")
    private String endereço;

    @CPF
    private String CPF;
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereço() {
        return endereço;
    }

    public void setEndereço(String endereço) {
        this.endereço = endereço;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }
    
}
