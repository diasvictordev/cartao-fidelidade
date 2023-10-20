package com.cartaofidelidade.cartaofidelidade.service;

import com.cartaofidelidade.cartaofidelidade.model.Loja;
import com.cartaofidelidade.cartaofidelidade.model.Produto;
import com.cartaofidelidade.cartaofidelidade.service.impl.ProdutoServiceImpl;

import java.util.List;
import java.util.Optional;

public interface ProdutoService {
    Produto cadastrarProduto(Produto produto, Loja loja);


    void excluirProduto(Long id);

    List<Produto> listarProdutosdaLoja(Loja loja);

    Optional<Produto> buscarProdutoPorId(Long id);

    Loja obterLojaDoProduto(Produto produto);
}
