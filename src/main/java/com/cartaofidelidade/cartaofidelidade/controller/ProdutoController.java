package com.cartaofidelidade.cartaofidelidade.controller;

import com.cartaofidelidade.cartaofidelidade.exceptions.RegraNegocioException;
import com.cartaofidelidade.cartaofidelidade.model.Loja;
import com.cartaofidelidade.cartaofidelidade.model.Produto;
import com.cartaofidelidade.cartaofidelidade.service.impl.LojaServiceImpl;
import com.cartaofidelidade.cartaofidelidade.service.impl.ProdutoServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/produto")
public class ProdutoController {

    public ProdutoServiceImpl produtoService;

    public ProdutoController (ProdutoServiceImpl produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping
    public ResponseEntity<?> cadastrarProduto(@RequestBody Produto produto, Loja loja){
        try {
            Produto produtoSalvo = produtoService.cadastrarProduto(produto, loja);
            return new ResponseEntity(produtoSalvo, HttpStatus.CREATED);
        }
        catch(RegraNegocioException e){
            return ResponseEntity.badRequest().body(e.getMessage());

        }
    }

    @GetMapping
    public ResponseEntity<?> listarProdutos(@RequestBody Loja loja){
        try {
            List <Produto> produtos = produtoService.listarProdutosdaLoja(loja);
            return new ResponseEntity(produtos, HttpStatus.OK);
        }
        catch(RegraNegocioException e){
            return ResponseEntity.badRequest().body(e.getMessage());

        }
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<?> excluirProduto(@PathVariable Long id){
        return produtoService.buscarProdutoPorId(id).map(entidade ->{
            produtoService.excluirProduto(entidade.getId());
            return new ResponseEntity<>( HttpStatus.NO_CONTENT);
        }).orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não foi possível excluir o produto!"));
    }


}
