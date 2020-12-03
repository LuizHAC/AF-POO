package com.example.demo.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
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

        //Verifica se as datas são válidas
        LocalDate inicio = LocalDate.now();

        if (reserva.getData_i().isAfter(reserva.getData_f())) {
            throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED,"Data final anterior a data inicial, selecione outra data!");
        } else if (inicio.isAfter(reserva.getData_i())) {
            throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED,"Data inicial anterior a data atual, selecione outra data!");
        } else if (reserva.getData_i().getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
            throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED,"Data inicial é um domingo, selecione outra data!");
        } else if (reserva.getData_f().getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
            throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED,"Data final é um domingo, selecione outra data!");
        }

        //Procurar o cliente e o veiculo
        Clientes cliente = clienteService.getClienteByCodigo(reserva.getCodCliente());
        Veiculos veiculo = veiculoService.getVeiculoByCodigo(reserva.getCodVeiculo());

        //Verificar se o veiculo esta reservado para estas datas
        List<Reserva> reservasVeiculos = veiculoService.getReservaByVeiculo(veiculo);

        for (Reserva aux : reservasVeiculos) {
            //DataIni da reserva deve ser depois da DataFin aux
            //DataFin da reserva deve ser antes da DataIni aux

            //Se a DataIni da reserva for antes da DataFin aux, a data pode estar ocupada
            if (!reserva.getData_i().isAfter(aux.getData_f())) {

                //Se a DataFin da reserva for depois da DataIni aux, a data está ocupada
                if (!reserva.getData_f().isBefore(aux.getData_i())) {
                    throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED,"Veículo reservado para a(s) data(s) inseridas, selecione outra data!");
                }
            }
        }

        //Vincular ao cliente e ao veiculo
        cliente.addReserva(reserva);
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

        //Verifica datas
        LocalDate inicio = LocalDate.now();

        if (reserva.getData_i().isAfter(reserva.getData_f())) {
            throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED,"Data final anterior a data inicial, selecione outra data!");
        } else if (inicio.isAfter(reserva.getData_i())) {
            new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED,"Data inicial anterior a data atual, selecione outra data!");
        } else if (reserva.getData_i().getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
            new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED,"Data inicial é um domingo, selecione outra data!");
        } else if (reserva.getData_f().getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
            new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED,"Data final é um domingo, selecione outra data!");
        }
        
        //Recuperar a reserva a ser atualizada e o veiculo
        Reserva resAntiga = getReservaByCodigo(reserva.getNumero());
        Veiculos veiculo = veiculoService.getVeiculoByCodigo(resAntiga.getCodVeiculo());
        
        //Recalcular o total usando o mesmo valor de diária da reserva antiga
        veiculo.setValor_diario(resAntiga.getTotal()/(resAntiga.getData_f().compareTo(resAntiga.getData_i())+1));
        reserva.setTotal(veiculo, 1 + reserva.getData_f().compareTo(reserva.getData_i()));
        
        return repository.updateReserva(reserva);
    }

    // DTO
    public Reserva fromDTO(ReservaDTO dto){
        Reserva reserva = new Reserva();

        //Pegar veiculo selecionado
        Veiculos veiculo = veiculoService.getVeiculoByCodigo(Integer.parseInt(dto.getCodVeiculo()));
        
        reserva.setCodCliente(Integer.parseInt(dto.getCodCliente()));
        reserva.setCodVeiculo(Integer.parseInt(dto.getCodVeiculo()));
        reserva.setData_i(dto.getData_i());
        reserva.setData_f(dto.getData_f());
        reserva.setTotal(veiculo, 1+dto.getData_f().compareTo(dto.getData_i()));
        return reserva;
    }
}
