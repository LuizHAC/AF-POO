package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import com.example.demo.model.Reserva;
import com.example.demo.model.Veiculos;

import org.springframework.stereotype.Component;

@Component
public class VeiculosRepository {

    private ArrayList<Veiculos> veiculos = new ArrayList<Veiculos>();
    private static int nextCodigo = 1;

    @PostConstruct
    public void criarVeiculos() {
        Veiculos c1 = new Veiculos();
        Veiculos c2 = new Veiculos();

        c1.setCodigo(nextCodigo++);
        c1.setModelo("Camaro");
        c1.setValor_diario(110.00);

        c2.setCodigo(nextCodigo++);
        c2.setModelo("Tesla Model S");
        c2.setValor_diario(90.00);

        veiculos.add(c1);
        veiculos.add(c2);

        nextCodigo = 3;
    }

    // 1 - Listar todos os veiculos
    public List<Veiculos> getAllVeiculos() {
        return veiculos;
    }

    // 2 - Buscar um veiculo pelo código
    public Optional<Veiculos> getVeiculoByCodigo(int código) {
        for (Veiculos aux : veiculos) {
            if (aux.getCodigo() == código) {
                return Optional.of(aux);
            }
        }
        return Optional.empty();
    }

    // 3 - Cadastrar um veiculo
    public Veiculos createVeiculo(Veiculos veiculo) {
        veiculo.setCodigo(nextCodigo++);
        veiculos.add(veiculo);
        return veiculo;
    }

    // 4 - Remover um veiculo
    public void removeVeiculo(Veiculos veiculo) {
        veiculos.remove(veiculo);
    }

    // 5 - Alterar um veiculo
    public Veiculos updateVeiculo(Veiculos veiculo) {
        Veiculos aux = getVeiculoByCodigo(veiculo.getCodigo()).get();

        if (aux != null) {
            aux.setValor_diario(veiculo.getValor_diario());
        }
        return aux;
    }

    // 6 - Listar todos as reservas do cliente
    public List<Reserva> getReservaByVeiculo(Veiculos veiculo) {
        List<Reserva> aux = veiculo.getReservas();
        return aux;
    }
}