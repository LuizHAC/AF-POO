package com.example.demo.dto;

import java.time.LocalDate;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ReservaDTO {

    @Positive(message = "Código do cliente deve ser um valor positivo!")
    @Pattern(message = "Código do cliente deve ser um valor numérico!", regexp="^[0-9]*$")
    private String codCliente;

    @Positive(message = "Código do veículo deve ser um valor positivo!")
    @Pattern(message = "Código do veículo deve ser um valor numérico!", regexp="^[0-9]*$")
    private String codVeiculo;
    
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate data_i;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate data_f;
    
    private double total;
    
    public String getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(String codCliente) {
        this.codCliente = codCliente;
    }

    public String getCodVeiculo() {
        return codVeiculo;
    }

    public void setCodVeiculo(String codVeiculo) {
        this.codVeiculo = codVeiculo;
    }

    public LocalDate getData_i() {
        return data_i;
    }

    public void setData_i(LocalDate data_i) {
        this.data_i = data_i;
    }

    public LocalDate getData_f() {
        return data_f;
    }

    public void setData_f(LocalDate data_f) {
        this.data_f = data_f;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
