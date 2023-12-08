package com.cartaofidelidade.cartaofidelidade.controller;


import com.cartaofidelidade.cartaofidelidade.exceptions.RegraNegocioException;
import com.cartaofidelidade.cartaofidelidade.model.Cliente;
import com.cartaofidelidade.cartaofidelidade.model.Loja;
import com.cartaofidelidade.cartaofidelidade.service.impl.LojaServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @PostMapping("login/admin")
    public ResponseEntity<?> autenticarAdmin(@RequestBody Loja loja){
        try {
            Loja lojaAutenticada = lojaService.autenticarAdmin(loja.getCnpj(), loja.getSenha(),loja.getPapel());
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

    @GetMapping("/{id}")
    public ResponseEntity<?> listarLojaPorId(@PathVariable Long id){
        try {
            Optional<Loja> loja = lojaService.listarLojaPorId(id);
            return new ResponseEntity(loja, HttpStatus.OK);
        }
        catch (RegraNegocioException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarLoja(@PathVariable Long id){
        return lojaService.listarLojaPorId(id).map(entidade ->{
            lojaService.excluirLoja(entidade.getId());
            return new ResponseEntity<>( HttpStatus.NO_CONTENT);
        }).orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao excluir!"));

    }
    @PutMapping
    public ResponseEntity<?> editarLoja(@RequestBody Loja loja){
        return lojaService.listarLojaPorId(loja.getId()).map(entidade -> {
            lojaService.cadastrarLoja(loja);
            return new ResponseEntity<>(HttpStatus.OK);
        }).orElseGet(() ->
                new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @PostMapping("/desativarloja")
    public ResponseEntity desativarLoja(@RequestBody Loja loja){
        lojaService.desativarLoja(loja);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/ativarloja")
    public ResponseEntity ativarLoja(@RequestBody Loja loja){
        lojaService.ativarLoja(loja);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
