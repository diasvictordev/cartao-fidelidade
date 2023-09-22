package com.cartaofidelidade.cartaofidelidade.controller;

import com.cartaofidelidade.cartaofidelidade.exceptions.RegraNegocioException;
import com.cartaofidelidade.cartaofidelidade.model.Cliente;
import com.cartaofidelidade.cartaofidelidade.service.impl.ClienteServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    public ClienteServiceImpl clienteService;

    public ClienteController (ClienteServiceImpl clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity salvar(@RequestBody Cliente cliente){
        Cliente clienteacadastar = Cliente.builder().nome(cliente.getNome()).
                cpf(cliente.getCpf()).
                endereco(cliente.getEndereco()).
                telefone(cliente.getTelefone()).
                dataNascimento(cliente.getDataNascimento()).
                senha(cliente.getSenha()).
                build();
        try {
            Cliente clienteSalvo = clienteService.cadastrarCliente(clienteacadastar);
            return new ResponseEntity(clienteSalvo, HttpStatus.CREATED);
        }
        catch(RegraNegocioException e){
            return ResponseEntity.badRequest().body(e.getMessage());

        }
    }
    @PostMapping("/login")
    public ResponseEntity autenticar(@RequestBody Cliente cliente){
        try {
            Cliente clienteautenticado = clienteService.autenticar(cliente.getCpf(), cliente.getSenha());
            return ResponseEntity.ok(clienteautenticado);
        }
        catch (RegraNegocioException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
