package com.example.demo.model;

import java.time.LocalDate;
import java.time.Period;

public class Reserva{
    
    private int número;
    private int codCliente;
    private int codVeículo;
    private LocalDate data_i;
    private LocalDate data_f;
    private double total;

    public int getNúmero() {
        return número;
    }

    public void setNúmero(int número) {
        this.número = número;
    }

    public int getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(int codCliente) {
        this.codCliente = codCliente;
    }

    public int getCodVeículo() {
        return codVeículo;
    }

    public void setCodVeículo(int codVeículo) {
        this.codVeículo = codVeículo;
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
    
    public void setTotal(Veículos veículo, LocalDate data_i, LocalDate data_f) {
        Period p = Period.between(data_f, data_i);
        int dias = p.getDays();
        this.total = dias * veículo.getValor_diario();
    }
}