package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.dto.ReservaDTO;
import com.example.demo.model.Clientes;
import com.example.demo.model.Reserva;
import com.example.demo.model.Veiculos;
import com.example.demo.repository.ReservaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ReservaService {
    
    @Autowired
    private ReservaRepository repository;

    @Autowired
    private ClientesService clienteService;

    @Autowired
    private VeiculosService veiculoService;

    // 1 - Listar todas as reservas
    public List<Reserva> getAllReservas() {
        return repository.getAllReservas();
    }

    // 2 - Buscar uma reserva pelo código
    public Reserva getReservaByCodigo(int código) {
        Optional<Reserva> aux = repository.getReservaByCodigo(código);
        return aux.orElseThrow( () ->
            new ResponseStatusException(
                HttpStatus.NOT_FOUND,"Reserva não encontrada"
            )
        );
    }

    // 3 - Cadastrar uma reserva
    public Reserva createReserva(Reserva reserva) {
        //Procurar o cliente
        Clientes cliente = clienteService.getClienteByCodigo(reserva.getCodCliente());

        //Procurar o veiculo
        Veiculos veiculo = veiculoService.getVeiculoByCodigo(reserva.getCodVeiculo());

        //Vincular ao cliente
        cliente.addReserva(reserva);

        //Vincular ao veiculo
        veiculo.addReserva(reserva);

        //Criar reserva
        return repository.createReserva(reserva);
    }

    // 4 - Remover uma reserva
    public Reserva removeReserva(Reserva reserva) {
        Optional<Reserva> aux = repository.getReservaByCodigo(reserva.getNumero());
        Clientes cliente = clienteService.getClienteByCodigo(reserva.getCodCliente());
        Veiculos veiculo = veiculoService.getVeiculoByCodigo(reserva.getCodVeiculo());
        
        if(!aux.isEmpty()){
            cliente.removeReserva(reserva);
            veiculo.removeReserva(reserva);
            repository.removeReserva(reserva);
        }

        return aux.orElseThrow( () -> 
            new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED,"")
        );
    }

    // 5 - Alterar uma reserva
    public Reserva updateReserva(Reserva reserva) {
        getReservaByCodigo(reserva.getNumero());
        return repository.updateReserva(reserva);
    }

    // DTO
    public Reserva fromDTO(ReservaDTO dto){
        Reserva reserva = new Reserva();
        reserva.setCodCliente(dto.getCodCliente());
        reserva.setCodVeiculo(dto.getCodVeiculo());
        reserva.setData_i(dto.getData_i());
        reserva.setData_f(dto.getData_f());
        return reserva;
    }
}
