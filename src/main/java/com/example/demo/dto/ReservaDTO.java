package com.example.demo.dto;

import java.time.LocalDate;

import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ReservaDTO {

    @Min(0)
    private int codCliente;

    @Min(0)
    private int codVeiculo;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate data_i;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate data_f;
    
    @Min(0)
    private double total;
    
    public int getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(int codCliente) {
        this.codCliente = codCliente;
    }

    public int getCodVeiculo() {
        return codVeiculo;
    }

    public void setCodVeiculo(int codVeiculo) {
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
