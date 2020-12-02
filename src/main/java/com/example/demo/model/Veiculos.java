package com.example.demo.model;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Veiculos {

    private int codigo;
    private String modelo;
    private double valor_diario;

    @JsonIgnore
    private ArrayList<Reserva> reservas = new ArrayList<Reserva>();

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

    public ArrayList<Reserva> getReservas() {
        return reservas;
    }

    public void getReservas(ArrayList<Reserva> reservas) {
        this.reservas = reservas;
    }

    public boolean addReserva(Reserva reserva) {
        return reservas.add(reserva);
    }

    public boolean removeReserva(Reserva reserva) {
        return reservas.remove(reserva);
    }
}