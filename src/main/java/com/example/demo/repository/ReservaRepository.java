package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import com.example.demo.model.Reserva;

import org.springframework.stereotype.Component;

@Component
public class ReservaRepository{

    private ArrayList<Reserva> reservas = new ArrayList<Reserva>();
    private static int nextNúmero = 1;

    @PostConstruct
    public void criarReservas(){
        Reserva c1 = new Reserva();
        Reserva c2 = new Reserva();
        
        c1.setNúmero(1);
        c1.setCodCliente(1);
        c1.setCodVeículo(1);


        c2.setNúmero(2);
        c2.setCodCliente(2);
        c2.setCodVeículo(2);

        reservas.add(c1);
        reservas.add(c2);

        nextNúmero =  3;
    }

    //1 - Listar reservas
    public List<Reserva> getAllReservas(){
        return reservas;
    }

    //2 - Listar uma reserva pelo código
    public Optional<Reserva> getReservaByCódigo(int número){
        for(Reserva aux : reservas){
            if(aux.getNúmero() == número){
                return Optional.of(aux);
            }
        }
        return Optional.empty();
    }

    //3 - Cadastra reserva
    public Reserva save(Reserva reserva){
        reserva.setNúmero(nextNúmero++);
        reservas.add(reserva);
        return reserva;
    }

    //4-Remover um reserva
    public void remove(Reserva reserva){
        nextNúmero--;
        reservas.remove(reserva);
    }

    //5-Alterar uma reserva
    public Reserva update(Reserva reserva){

        Reserva aux = getReservaByCódigo(reserva.getNúmero()).get();

        if(aux != null){
            aux.setCodCliente(reserva.getCodCliente());
            aux.setCodVeículo(reserva.getCodVeículo());
        }

        return aux;
    }

}