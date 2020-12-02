package com.example.demo.model;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Clientes {

    private int codigo;
    private String nome;
    private String endereço;
    private String CPF;

    @JsonIgnore
    private ArrayList<Reserva> reservas = new ArrayList<Reserva>();

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

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