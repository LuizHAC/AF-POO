package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.example.demo.dto.ClientesDTO;
import com.example.demo.model.Clientes;
import com.example.demo.model.Reserva;
import com.example.demo.service.ClientesService;

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
@RequestMapping("/clientes")
public class ClientesController {
    
    @Autowired
    private ClientesService clienteService;

    // 1 - Listar todos os clientes
    @GetMapping()
    public List<Clientes> getAllClientes() {
        return clienteService.getAllClientes();
    }

    // 2 - Buscar um cliente pelo código
    @GetMapping("/{codigo}")
    public ResponseEntity<Clientes> getClienteByCodigo(@PathVariable int codigo) {
        Clientes cliente = clienteService.getClienteByCodigo(codigo);
        return ResponseEntity.ok(cliente);
    }

    // 3 - Cadastrar um cliente
    @PostMapping()
    public ResponseEntity<Clientes> createCliente(
        @RequestBody ClientesDTO clienteDTO,
        HttpServletRequest request,
        UriComponentsBuilder builder
    ){
        Clientes escola = clienteService.fromDTO(clienteDTO);
        Clientes novoCliente = clienteService.createCliente(escola);
        UriComponents uriComponents = builder.path(request.getRequestURI() + "/" + novoCliente.getCodigo()).build();
        return ResponseEntity.created(uriComponents.toUri()).build();
    }

    // 4 - Remover um cliente
    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> removeCliente(@PathVariable int codigo) {
        Clientes cliente = clienteService.getClienteByCodigo(codigo);
        clienteService.removeCliente(cliente);
        return ResponseEntity.noContent().build();
    }

    // 5 - Alterar um cliente
    @PutMapping("/{codigo}")
    public ResponseEntity<Clientes> updateCliente(@RequestBody ClientesDTO clienteDTO, @PathVariable int codigo) {
        Clientes cliente = clienteService.fromDTO(clienteDTO);
        cliente.setCodigo(codigo);
        cliente = clienteService.updateCliente(cliente);
        return ResponseEntity.ok(cliente);
    }

    // 6 - Listar todas as reservas do cliente
    @GetMapping("/{codigo}/reservas")
    public List<Reserva> getReservasByCliente(@PathVariable int codigo) {
        Clientes cliente = clienteService.getClienteByCodigo(codigo);
        return clienteService.getReservasByCliente(cliente);
    }
}
