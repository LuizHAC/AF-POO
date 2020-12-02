package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.Clientes;
import com.example.demo.model.Reserva;
import com.example.demo.repository.ClientesRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ClientesService {

    @Autowired
    private ClientesRepository repository;

    // 1 - Listar todos os clientes
    public List<Clientes> getAllClientes() {
        return repository.getAllClientes();
    }

    // 2 - Buscar um cliente pelo código
    public Clientes getClienteByCódigo(int código) {
        Optional<Clientes> aux = repository.getClienteByCódigo(código);
        return aux.orElseThrow( () ->
            new ResponseStatusException(
                HttpStatus.NOT_FOUND,"Cliente não encontrado"
            )
        );
    }

    // 3 - Cadastrar um cliente
    public Clientes createCliente(Clientes cliente) {
        return repository.createCliente(cliente);
    }

    // 4 - Remover um cliente
    public Clientes removeCliente(Clientes cliente) {
        List<Reserva> reservas = getReservasByCliente(cliente);
        Optional<Clientes> aux = repository.getClienteByCódigo(cliente.getCodigo());

        if (reservas.isEmpty()) {
            repository.removeCliente(cliente);
        } else {
            aux = Optional.empty();
        }

        return aux.orElseThrow( () ->
            new ResponseStatusException(
                HttpStatus.METHOD_NOT_ALLOWED, "Cliente possui reservas ativas"
            )
        );
    }

    // 5 - Alterar um cliente
    public Clientes updateCliente(Clientes cliente) {
        getClienteByCódigo(cliente.getCodigo());
        return repository.updateCliente(cliente);
    }

    // 6 - Listar todos as reservas do cliente
    public List<Reserva> getReservasByCliente(Clientes cliente) {
        List<Reserva> reservas = repository.getReservasByCliente(cliente);
        return reservas;
    }
}
