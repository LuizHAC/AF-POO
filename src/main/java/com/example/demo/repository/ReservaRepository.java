package com.example.demo.repository;

import java.util.ArrayList;

import javax.annotation.PostConstruct;

import com.example.demo.model.Reserva;

import org.springframework.stereotype.Component;

@Component
public class ReservaRepository{

    private ArrayList<Reserva> reservas = new ArrayList<Reserva>();
    private static int nextCodigo;

    @PostConstruct
    public void criarReservas(){
        Reserva c1 = new Reserva();
        Reserva c2 = new Reserva();

        

        reservas.add(c1);
        reservas.add(c2);

        nextCodigo =  3;
    }



}