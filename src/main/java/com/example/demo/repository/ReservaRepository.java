package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import com.example.demo.model.Reserva;

import org.springframework.stereotype.Component;

@Component
public class ReservaRepository {

    private ArrayList<Reserva> reservas = new ArrayList<Reserva>();
    private static int nextNumero = 1;

    @PostConstruct
    public void criarReservas() {
    }

    // 1 - Listar todas as reservas
    public List<Reserva> getAllReservas() {
        return reservas;
    }

    // 2 - Buscar uma reserva pelo código
    public Optional<Reserva> getReservaByCodigo(int número) {
        for (Reserva aux : reservas) {
            if (aux.getNumero() == número) {
                return Optional.of(aux);
            }
        }
        return Optional.empty();
    }

    // 3 - Cadastra uma reserva
    public Reserva createReserva(Reserva reserva) {
        reserva.setNumero(nextNumero++);
        reservas.add(reserva);
        return reserva;
    }

    // 4 - Remover um reserva
    public void removeReserva(Reserva reserva) {
        reservas.remove(reserva);
    }

    // 5 - Alterar uma reserva
    public Reserva updateReserva(Reserva reserva) {
        Reserva aux = getReservaByCodigo(reserva.getNumero()).get();

        if (aux != null) {
            aux.setCodCliente(reserva.getCodCliente());
            aux.setCodVeiculo(reserva.getCodVeiculo());
        }
        return aux;
    }
}