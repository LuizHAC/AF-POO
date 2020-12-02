package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.Reserva;
import com.example.demo.repository.ReservaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ReservaService {
    
    @Autowired
    private ReservaRepository repository;

    // 1 - Listar todas as reservas
    public List<Reserva> getAllReservas() {
        return repository.getAllReservas();
    }

    // 2 - Buscar uma reserva pelo código
    public Reserva getClienteByCódigo(int código) {
        Optional<Reserva> aux = repository.getReservaByCódigo(código);
        return aux.orElseThrow( () ->
            new ResponseStatusException(
                HttpStatus.NOT_FOUND,"Reserva não encontrada"
            )
        );
    }

    // 3 - Cadastrar uma reserva
    public Reserva createReserva(Reserva reserva) {
        return repository.createReserva(reserva);
    }

    // 4 - Remover uma reserva
    public Reserva remoReserva(Reserva reserva) {
        Optional<Reserva> aux = repository.getReservaByCódigo(reserva.getNúmero());

        if(!aux.isEmpty()){
            repository.removeReserva(reserva);
        }

        return aux.orElseThrow( () -> 
            new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED,"")
        );
    }

    // 5 - Alterar uma reserva
    public Reserva updateReserva(Reserva reserva) {
        getClienteByCódigo(reserva.getNúmero());
        return repository.updateReserva(reserva);
    }
}
