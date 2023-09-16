package com.cartaofidelidade.cartaofidelidade.controller;

import com.cartaofidelidade.cartaofidelidade.service.impl.ClienteServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    public ClienteServiceImpl clienteService;

    public ClienteController (ClienteServiceImpl clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public String cadastrarCliente(){
        return "ola";
    }

}
