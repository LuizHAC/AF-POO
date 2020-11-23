package com.example.demo.model;

import java.util.Date;

public class Reserva{
    
    private int número;
    private Clientes cliente;
    private Veículos veículo;
    private Date data_i;
    private Date data_f;
    private double total;

    public int getNúmero(){
        return número;
    }

    public void setNúmero(int número){
        this.número = número;
    }

    public Clientes getCliente(){
        return cliente;
    }

    public void setCliente(Cliente cliente){
        this.cliente = cliente;
    }

    public Veículos getVeículos(){
        return veículo;
    }

    public void setVeículos(Veículos veículo){
        this.veículo = veículo;
    }

    private Date getData_i(){
        return data_i;
    }

    private void setData_i(Date data_i){
        this.data_i = data_i;
    }

    private Date getData_f(){
        return data_f;
    }

    private void setData_f(Date data_f){
        this.data_f = data_f;
    }

    private void setTotal(Veículos veículo, Date data_i, Date data_f){
        int data_dif;
        
    }

}