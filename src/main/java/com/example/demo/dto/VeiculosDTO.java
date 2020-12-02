package com.example.demo.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

public class VeiculosDTO {
    
    @NotBlank(message = "Modelo obrigatório!")
    @Length(min = 4, max = 20, message = "Modelo deve ter no mínimo 4 e no máximo 20 caracteres")
    private String modelo;

    @Min(0)
    private double valor_diario;
    
    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public double getValor_diario() {
        return valor_diario;
    }

    public void setValor_diario(double valor_diario) {
        this.valor_diario = valor_diario;
    }

}
