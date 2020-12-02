package com.example.demo.model;

public class Veículos {

    private int codigo;
    private String modelo;
    private double valor_diario;

    public int getCodigo(){
        return codigo;
    }

    public void setCodigo(int codigo){
        this.codigo = codigo;
    }

    public String getModelo(){
        return modelo;
    }

    public void setModelo(String modelo){
        this.modelo = modelo;
    }

    public double getValor_diario(){
        return valor_diario;
    }

    public void setValor_diario(double valor_diario){
        this.valor_diario = valor_diario;
    }
}