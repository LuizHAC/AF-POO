package com.example.demo.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.Length;

public class VeiculosDTO {
    
    @NotBlank(message = "Modelo obrigatório!")
    @Length(min = 4, max = 20, message = "Modelo deve ter no mínimo 4 e no máximo 20 caracteres")
    private String modelo;

    @Positive(message = "Valor diário deve ser um valor positivo!")
    @Pattern(message = "Valor diário deve ser um valor numérico", regexp="^[0-9 .]*$")
    private String valor_diario;
    
    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getValor_diario() {
        return valor_diario;
    }

    public void setValor_diario(String valor_diario) {
        this.valor_diario = valor_diario;
    }

}
