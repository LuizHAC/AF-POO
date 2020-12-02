package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.example.demo.dto.VeiculosDTO;
import com.example.demo.model.Reserva;
import com.example.demo.model.Veiculos;
import com.example.demo.service.VeiculosService;

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
@RequestMapping("/veiculos")
public class VeiculosController {
    
    @Autowired
    private VeiculosService veiculoService;

    // 1 - Listar todos os veiculo
    @GetMapping()
    public List<Veiculos> getAllVeiculos() {
        return veiculoService.getAllVeiculos();
    }

    // 2 - Buscar um veiculo pelo código
    @GetMapping("/{codigo}")
    public ResponseEntity<Veiculos> getVeiculoByCodigo(@PathVariable int codigo) {
        Veiculos veiculo = veiculoService.getVeiculoByCodigo(codigo);
        return ResponseEntity.ok(veiculo);
    }

    // 3 - Cadastrar um veiculo
    @PostMapping()
    public ResponseEntity<Veiculos> createVeiculo(
        @RequestBody VeiculosDTO veiculoDTO,
        HttpServletRequest request,
        UriComponentsBuilder builder
    ){
        Veiculos veiculo = veiculoService.fromDTO(veiculoDTO);
        Veiculos novoVeiculo = veiculoService.createVeiculo(veiculo);
        UriComponents uriComponents = builder.path(request.getRequestURI() + "/" + novoVeiculo.getCodigo()).build();
        return ResponseEntity.created(uriComponents.toUri()).build();
    }

    // 4 - Remover um veiculo
    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> removeVeiculo(@PathVariable int codigo) {
        Veiculos veiculo = veiculoService.getVeiculoByCodigo(codigo);
        veiculoService.removeVeiculo(veiculo);
        return ResponseEntity.noContent().build();
    }

    // 5 - Alterar um veiculo
    @PutMapping("/{codigo}")
    public ResponseEntity<Veiculos> updateVeiculo(@RequestBody VeiculosDTO veiculoDTO, @PathVariable int codigo) {
        Veiculos veiculo = veiculoService.fromDTO(veiculoDTO);
        veiculo.setCodigo(codigo);
        veiculo = veiculoService.updateVeiculo(veiculo);
        return ResponseEntity.ok(veiculo);
    }

    // 6 - Listar todas as reservas de um veiculo
    @GetMapping("/{codigo}/reservas")
    public List<Reserva> getReservaByVeiculo(@PathVariable int codigo) {
        Veiculos veiculo = veiculoService.getVeiculoByCodigo(codigo);
        return veiculoService.getReservaByVeiculo(veiculo);
    }
}
