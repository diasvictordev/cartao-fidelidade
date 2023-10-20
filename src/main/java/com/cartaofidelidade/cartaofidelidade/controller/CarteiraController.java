package com.cartaofidelidade.cartaofidelidade.controller;

import com.cartaofidelidade.cartaofidelidade.exceptions.RegraNegocioException;
import com.cartaofidelidade.cartaofidelidade.model.Carteira;
import com.cartaofidelidade.cartaofidelidade.model.Cliente;
import com.cartaofidelidade.cartaofidelidade.model.Loja;
import com.cartaofidelidade.cartaofidelidade.service.impl.LojaServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/carteira")
public class CarteiraController {

    public LojaServiceImpl lojaService;

    public CarteiraController (LojaServiceImpl lojaService) {
        this.lojaService = lojaService;
    }

    @PostMapping
    public ResponseEntity<?> criarCarteira(@RequestBody Cliente cliente, Loja loja, Carteira carteira){
        try {
            Carteira carteirasalva = lojaService.criarCarteira(cliente, loja, carteira);
            return new ResponseEntity(carteirasalva, HttpStatus.CREATED);
        }
        catch(RegraNegocioException e){
            return ResponseEntity.badRequest().body(e.getMessage());

        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> adicionarPontosaCarteira(@PathVariable Long id, @RequestBody Integer pontos){
        try {
            Optional<Carteira> carteirasalva = lojaService.procurarCarteiraporId(id);
            lojaService.adicionarPontosNaCarteira(id, pontos);
            return new ResponseEntity(carteirasalva, HttpStatus.OK);
        }
        catch(RegraNegocioException e){
            return ResponseEntity.badRequest().body(e.getMessage());

        }
    }

}
