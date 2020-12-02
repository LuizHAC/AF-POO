package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.Reserva;
import com.example.demo.model.Veículos;
import com.example.demo.repository.VeículosRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class VeículosService {

    @Autowired
    private VeículosRepository repository;

    // 1 - Listar todos sos veículos
    public List<Veículos> getAllVeículos() {
        return repository.getAllVeículos();
    }

    // 2 - Buscar um veículo pelo código
    public Veículos getVeículoByCódigo(int código) {
        Optional<Veículos> aux = repository.getVeículoByCódigo(código);
        return aux.orElseThrow( () ->
            new ResponseStatusException(
                HttpStatus.NOT_FOUND,"Veículo não encontrado"
            )
        );
    }

    // 3 - Cadastrar um veículo
    public Veículos createVeículo(Veículos veiculo) {
        return repository.createVeículo(veiculo);
    }

    // 4 - Remover um veículo
    public Veículos removeVeículo(Veículos veiculo) {
        List<Reserva> reservas = getReservaByVeículo(veiculo);
        Optional<Veículos> aux = repository.getVeículoByCódigo(veiculo.getCodigo());

        if (reservas.isEmpty()) {
            repository.removeVeículo(veiculo);
        } else {
            aux = Optional.empty();
        }

        return aux.orElseThrow( () ->
            new ResponseStatusException(
                HttpStatus.METHOD_NOT_ALLOWED, "Veículo possui reservas ativas"
            )
        );
    }

    // 5 - Alterar um veículo
    public Veículos updateVeículo(Veículos veiculo) {
        getVeículoByCódigo(veiculo.getCodigo());
        return repository.updateVeículo(veiculo);
    }

    // 6 - Listar todos as reservas do veículo
    public List<Reserva> getReservaByVeículo(Veículos veiculo) {
        List<Reserva> reservas = repository.getReservaByVeículo(veiculo);
        return reservas;
    }
}
