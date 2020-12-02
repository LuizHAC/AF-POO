package com.example.demo.model;

import java.time.LocalDate;

public class Reserva{
    
    private int numero;
    private int codCliente;
    private int codVeiculo;
    private LocalDate data_i;
    private LocalDate data_f;
    private double total;

    public int getNumero() {
        return numero;
    }

    public void setNumero(int número) {
        this.numero = número;
    }

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

    public double getTotal(){
        return total;
    }
    
    public void setTotal(Veiculos veiculo, int dias) {
        this.total = dias * veiculo.getValor_diario();
    }
}