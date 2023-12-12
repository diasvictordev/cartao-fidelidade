package com.cartaofidelidade.cartaofidelidade.controller;

import com.cartaofidelidade.cartaofidelidade.exceptions.RegraNegocioException;
import com.cartaofidelidade.cartaofidelidade.model.Cliente;
import com.cartaofidelidade.cartaofidelidade.service.impl.ClienteServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    public ClienteServiceImpl clienteService;

    public ClienteController (ClienteServiceImpl clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody Cliente cliente){
        try {
            Cliente clienteSalvo = clienteService.cadastrarCliente(cliente);
            return new ResponseEntity(clienteSalvo, HttpStatus.CREATED);
        }
        catch(RegraNegocioException e){
            return ResponseEntity.badRequest().body(e.getMessage());

        }
    }
    @PostMapping("/login")
    public ResponseEntity<?> autenticar(@RequestBody Cliente cliente){
        try {
            Cliente clienteautenticado = clienteService.autenticar(cliente.getCpf(), cliente.getSenha());
            return ResponseEntity.ok("Logado!");
        }
        catch (RegraNegocioException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> listarClientes(){
        List<Cliente> clientes = clienteService.listarClientes();
        if(clientes.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não existem clientes cadastrados!");
        }
        else {
            return ResponseEntity.status(HttpStatus.OK).body(clientes);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarClientePorId(@PathVariable Long id){
        return clienteService.buscarClienteporId(id).map(entidade -> new ResponseEntity<>(entidade, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<?> buscarClientePorCpf(@PathVariable String cpf){
        return clienteService.buscarClienteporCpf(cpf).map(entidade -> new ResponseEntity<>(entidade, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarCliente(@PathVariable Long id){
        return clienteService.buscarClienteporId(id).map(entidade ->{
            clienteService.excluirConta(entidade.getId());
            return new ResponseEntity<>( HttpStatus.NO_CONTENT);
        }).orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao excluir!"));

    }

    @PutMapping
    public ResponseEntity<?> editarCliente(@RequestBody Cliente cliente){
        return clienteService.buscarClienteporId(cliente.getId()).map(entidade -> {
            clienteService.cadastrarCliente(cliente);
            return new ResponseEntity<>(HttpStatus.OK);
        }).orElseGet(() ->
                new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

}
