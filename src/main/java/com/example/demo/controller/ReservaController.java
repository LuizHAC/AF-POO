package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.example.demo.dto.ReservaDTO;
import com.example.demo.model.Reserva;
import com.example.demo.service.ReservaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    // 1 - Listar todas as reservas
    @GetMapping()
    public List<Reserva> getAllReservas() {
        return reservaService.getAllReservas();
    }

    // 2 - Buscar uma reserva pelo código
    @GetMapping("/{codigo}")
    public ResponseEntity<Reserva> getReservaByCodigo(@PathVariable int codigo) {
        Reserva reserva = reservaService.getReservaByCodigo(codigo);
        return ResponseEntity.ok(reserva);
    }

    // 3 - Cadastrar uma reserva
    @PostMapping()
    public ResponseEntity<Reserva> createCliente(
        @Valid @RequestBody ReservaDTO reservaDTO,
        HttpServletRequest request,
        UriComponentsBuilder builder
    ){
        Reserva reserva = reservaService.fromDTO(reservaDTO);
        if (reserva.getNumero() == -1) {
            return ResponseEntity.badRequest().build();
        }
        Reserva novaReserva = reservaService.createReserva(reserva);
        UriComponents uriComponents = builder.path(request.getRequestURI() + "/" + novaReserva.getNumero()).build();
        return ResponseEntity.created(uriComponents.toUri()).build();
    }

    // 4 - Remover uma reserva
    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> removeCliente(@PathVariable int codigo) {
        Reserva reserva = reservaService.getReservaByCodigo(codigo);
        reservaService.removeReserva(reserva);
        return ResponseEntity.noContent().build();
    }

    // 5 - Alterar uma reserva
    @PutMapping("/{codigo}")
    public ResponseEntity<Reserva> updateCliente(
        @Valid @RequestBody ReservaDTO reservaDTO,
        @PathVariable int codigo
    ) {
        Reserva reserva = reservaService.fromDTO(reservaDTO);
        reserva.setNumero(codigo);
        reserva = reservaService.updateReserva(reserva);
        return ResponseEntity.ok(reserva);
    }
}
