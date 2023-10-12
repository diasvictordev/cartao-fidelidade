package com.cartaofidelidade.cartaofidelidade.service;

import com.cartaofidelidade.cartaofidelidade.model.Loja;
import com.cartaofidelidade.cartaofidelidade.model.Produto;
import com.cartaofidelidade.cartaofidelidade.service.impl.ProdutoServiceImpl;

import java.util.List;

public interface ProdutoService {
    Produto cadastrarProduto(Produto produto, Loja loja);

    void excluirProduto(Produto produto, Loja loja);

    List<Produto> listarProdutosdaLoja(Loja loja);
}
