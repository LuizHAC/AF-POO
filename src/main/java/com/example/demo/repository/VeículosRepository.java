package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import com.example.demo.model.Veículos;

import org.springframework.stereotype.Component;

@Component
public class VeículosRepository{

    private ArrayList<Veículos> veiculos = new ArrayList<Veículos>();
    private static int nextCodigo;

    @PostConstruct
    public void criarVeículos(){
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

        nextCodigo =  3;
    }

    //1 - Listar os veiculos
    public List<Veículos> getAllVeículos(){
        return veiculos;
    }

    //2 - Listar um veiculo pelo código
    public Optional<Veículos> getVeículoByCódigo(int código){
        for(Veículos aux : veiculos){
            if(aux.getCodigo() == código){
                return Optional.of(aux);
            }
        }
        return Optional.empty();
    }

    //3 - Cadastra veiculo
    public Veículos save(Veículos veiculo){
        veiculo.setCodigo(nextCodigo++);
        veiculos.add(veiculo);
        return veiculo;
    }

    //4-Remover um veiculo
    public void remove(Veículos veiculo){
        nextCodigo--;
        veiculos.remove(veiculo);
    }

    //5-Alterar um veiculo
    public Veículos update(Veículos veiculo){

        Veículos aux = getVeículoByCódigo(veiculo.getCodigo()).get();

        if(aux != null){
            aux.setModelo(veiculo.getModelo());
        }

        return aux;
    }

    
}