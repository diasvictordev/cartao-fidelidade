package com.cartaofidelidade.cartaofidelidade.controller;


import com.cartaofidelidade.cartaofidelidade.exceptions.RegraNegocioException;
import com.cartaofidelidade.cartaofidelidade.model.Loja;
import com.cartaofidelidade.cartaofidelidade.service.impl.LojaServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loja")
public class LojaController {

    public LojaServiceImpl lojaService;

    public LojaController (LojaServiceImpl lojaService) {
        this.lojaService = lojaService;
    }

    @PostMapping
    public ResponseEntity<?> salvarLoja(@RequestBody Loja loja){
        try {
            Loja lojaSalva = lojaService.cadastrarLoja(loja);
            return new ResponseEntity(lojaSalva, HttpStatus.CREATED);
        }
        catch(RegraNegocioException e){
            return ResponseEntity.badRequest().body(e.getMessage());

        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> autenticarLoja(@RequestBody Loja loja){
        try {
            Loja lojaAutenticada = lojaService.autenticar(loja.getCnpj(), loja.getSenha());
            return ResponseEntity.ok("Logado!");
        }
        catch (RegraNegocioException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> listarLojas(){
        try {
            List<Loja> lojas = lojaService.listarLojas();
            return new ResponseEntity(lojas, HttpStatus.OK);
        }
        catch (RegraNegocioException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
