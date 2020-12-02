package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import com.example.demo.model.Reserva;
import com.example.demo.model.Veículos;

import org.springframework.stereotype.Component;

@Component
public class VeículosRepository {

    private ArrayList<Veículos> veiculos = new ArrayList<Veículos>();
    private static int nextCodigo;
    private ReservaRepository reservaRepository;

    @PostConstruct
    public void criarVeículos() {
        Veículos c1 = new Veículos();
        Veículos c2 = new Veículos();

        c1.setCodigo(1);
        c1.setModelo("Camaro");
        c1.setValor_diario(110.00);

        c2.setCodigo(2);
        c2.setModelo("Tesla Model S");
        c2.setValor_diario(90.00);

        veiculos.add(c1);
        veiculos.add(c2);

        nextCodigo = 3;
    }

    // 1 - Listar todos os veiculos
    public List<Veículos> getAllVeículos() {
        return veiculos;
    }

    // 2 - Buscar um veiculo pelo código
    public Optional<Veículos> getVeículoByCódigo(int código) {
        for (Veículos aux : veiculos) {
            if (aux.getCodigo() == código) {
                return Optional.of(aux);
            }
        }
        return Optional.empty();
    }

    // 3 - Cadastrar um veiculo
    public Veículos createVeículo(Veículos veiculo) {
        veiculo.setCodigo(nextCodigo++);
        veiculos.add(veiculo);
        return veiculo;
    }

    // 4 - Remover um veiculo
    public void removeVeículo(Veículos veiculo) {
        veiculos.remove(veiculo);
    }

    // 5 - Alterar um veiculo
    public Veículos updateVeículo(Veículos veiculo) {
        Veículos aux = getVeículoByCódigo(veiculo.getCodigo()).get();

        if (aux != null) {
            aux.setValor_diario(veiculo.getValor_diario());
        }
        return aux;
    }

    // 6 - Listar todas as reservas do veículo
    public List<Reserva> getReservaByVeículo(Veículos veiculo) {
        List<Reserva> reservas = reservaRepository.getAllReservas();
        ArrayList<Reserva> reservasByVeiculo = new ArrayList<Reserva>();

        for (Reserva aux : reservas) {
            if (aux.getCodVeículo() == veiculo.getCodigo()) {
                reservasByVeiculo.add(aux);
            }
        }

        return reservasByVeiculo;
    }
}