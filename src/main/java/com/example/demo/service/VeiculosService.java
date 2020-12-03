package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.dto.VeiculosDTO;
import com.example.demo.model.Reserva;
import com.example.demo.model.Veiculos;
import com.example.demo.repository.VeiculosRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class VeiculosService {

    @Autowired
    private VeiculosRepository repository;

    // 1 - Listar todos sos veículos
    public List<Veiculos> getAllVeiculos() {
        return repository.getAllVeiculos();
    }

    // 2 - Buscar um veículo pelo código
    public Veiculos getVeiculoByCodigo(int código) {
        Optional<Veiculos> aux = repository.getVeiculoByCodigo(código);
        return aux.orElseThrow( () ->
            new ResponseStatusException(
                HttpStatus.NOT_FOUND,"Veículo não encontrado"
            )
        );
    }

    // 3 - Cadastrar um veículo
    public Veiculos createVeiculo(Veiculos veiculo) {
        return repository.createVeiculo(veiculo);
    }

    // 4 - Remover um veículo
    public Veiculos removeVeiculo(Veiculos veiculo) {
        List<Reserva> reservas = getReservaByVeiculo(veiculo);
        Optional<Veiculos> aux = repository.getVeiculoByCodigo(veiculo.getCodigo());

        if (reservas.isEmpty()) {
            repository.removeVeiculo(veiculo);
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
    public Veiculos updateVeiculo(Veiculos veiculo) {
        getVeiculoByCodigo(veiculo.getCodigo());
        return repository.updateVeiculo(veiculo);
    }

    // 6 - Listar todos as reservas do veículo
    public List<Reserva> getReservaByVeiculo(Veiculos veiculo) {
        List<Reserva> reservas = repository.getReservaByVeiculo(veiculo);
        return reservas;
    }

    // DTO
    public Veiculos fromDTO(VeiculosDTO dto){
        Veiculos veiculo = new Veiculos();
        veiculo.setModelo(dto.getModelo());
        veiculo.setValor_diario(Double.parseDouble(dto.getValor_diario()));
        return veiculo;
    }
}
