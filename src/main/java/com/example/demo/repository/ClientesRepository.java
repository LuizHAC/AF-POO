package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import com.example.demo.model.Clientes;
import com.example.demo.model.Reserva;

import org.springframework.stereotype.Component;

@Component
public class ClientesRepository {

    private ArrayList<Clientes> clientes = new ArrayList<Clientes>();
    private static int nextCodigo;
    private ReservaRepository reservaRepository;

    @PostConstruct
    public void criarClientes() {
        Clientes c1 = new Clientes();
        Clientes c2 = new Clientes();

        c1.setCodigo(1);
        c1.setNome("Luiz Henrique Aguiar");
        c1.setCPF("456.768.358-39");
        c1.setEndereço("Rua Caramelo Real");

        c2.setCodigo(2);
        c2.setNome("Gabriel Nunes");
        c2.setCPF("366.883.798-50");
        c2.setEndereço("Rua Sorvete de Creme");

        clientes.add(c1);
        clientes.add(c2);

        nextCodigo = 3;
    }

    // 1 - Listar todos os clientes
    public List<Clientes> getAllClientes() {
        return clientes;
    }

    // 2 - Buscar um cliente pelo código
    public Optional<Clientes> getClienteByCódigo(int código) {
        for (Clientes aux : clientes) {
            if (aux.getCodigo() == código) {
                return Optional.of(aux);
            }
        }
        return Optional.empty();
    }

    // 3 - Cadastrar um cliente
    public Clientes createCliente(Clientes cliente) {
        cliente.setCodigo(nextCodigo++);
        clientes.add(cliente);
        return cliente;
    }

    // 4 - Remover um cliente
    public void removeCliente(Clientes cliente) {
        clientes.remove(cliente);
    }

    // 5 - Alterar um cliente
    public Clientes updateCliente(Clientes cliente) {
        Clientes aux = getClienteByCódigo(cliente.getCodigo()).get();

        if (aux != null) {
            aux.setNome(cliente.getNome());
            aux.setEndereço(cliente.getEndereço());
        }
        return aux;
    }

    // 6 - Listar todas as reservas do cliente
    public List<Reserva> getReservasByCliente(Clientes cliente) {
        List<Reserva> reservas = reservaRepository.getAllReservas();
        ArrayList<Reserva> reservasByCliente = new ArrayList<Reserva>();

        for (Reserva aux : reservas) {
            if (aux.getCodCliente() == cliente.getCodigo()) {
                reservasByCliente.add(aux);
            }
        }

        return reservasByCliente;
    }
}