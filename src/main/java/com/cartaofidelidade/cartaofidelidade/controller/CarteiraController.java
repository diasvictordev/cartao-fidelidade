package com.cartaofidelidade.cartaofidelidade.controller;

import com.cartaofidelidade.cartaofidelidade.exceptions.RegraNegocioException;
import com.cartaofidelidade.cartaofidelidade.model.Carteira;
import com.cartaofidelidade.cartaofidelidade.model.Cliente;
import com.cartaofidelidade.cartaofidelidade.model.Loja;
import com.cartaofidelidade.cartaofidelidade.service.impl.LojaServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/carteira")
public class CarteiraController {

    public LojaServiceImpl lojaService;

    public CarteiraController (LojaServiceImpl lojaService) {
        this.lojaService = lojaService;
    }

    @PostMapping
    public ResponseEntity<?> criarCarteira(@RequestBody Carteira carteira) {
        try {
            Carteira carteiraASalvar = lojaService.criarCarteira(
                    carteira.getQuantidadePontos(),
                    carteira.getCliente().getId(),
                    carteira.getLoja().getId()
            );
            return new ResponseEntity<>(carteira, HttpStatus.CREATED);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> adicionarPontosaCarteira(@PathVariable Long id, @RequestBody Integer pontos){
        try {
            Carteira carteirasalva = lojaService.procurarCarteiraporId(id);
            lojaService.mudarPontosNaCarteira(carteirasalva, pontos);
            return new ResponseEntity(carteirasalva, HttpStatus.OK);
        }
        catch(RegraNegocioException e){
            return ResponseEntity.badRequest().body(e.getMessage());

        }
    }

    @GetMapping("{id}")
    public ResponseEntity<?> listarCarteiraPorId(@PathVariable Long id){
        try {
            Carteira carteira = lojaService.procurarCarteiraporId(id);
            return new ResponseEntity(carteira, HttpStatus.OK);
        }
        catch(RegraNegocioException e){
            return ResponseEntity.badRequest().body(e.getMessage());

        }
    }

    @GetMapping
    public ResponseEntity<?> listarCarteiras(){
        try {
            List<Carteira> carteira = lojaService.listarCarteiras();
            return new ResponseEntity(carteira, HttpStatus.OK);
        }
        catch(RegraNegocioException e){
            return ResponseEntity.badRequest().body(e.getMessage());

        }
    }

    @GetMapping("/pontos/usuario")
    public ResponseEntity<?> buscarPontosPorUsuario(@RequestBody Long id){
        try {
            Integer pontos = lojaService.buscarQuantidadePontosCarteiraDoUsuario(id);
            return new ResponseEntity(pontos, HttpStatus.OK);
        }
        catch(RegraNegocioException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarCarteira(@PathVariable Long id){
        try {
            lojaService.deletarCarteira(id);
            return new ResponseEntity(HttpStatus.OK);
        }
        catch(RegraNegocioException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
