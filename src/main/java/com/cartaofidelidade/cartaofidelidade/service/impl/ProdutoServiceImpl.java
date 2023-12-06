package com.cartaofidelidade.cartaofidelidade.service.impl;

import com.cartaofidelidade.cartaofidelidade.exceptions.RegraNegocioException;
import com.cartaofidelidade.cartaofidelidade.model.Loja;
import com.cartaofidelidade.cartaofidelidade.model.Produto;
import com.cartaofidelidade.cartaofidelidade.repository.LojaRepository;
import com.cartaofidelidade.cartaofidelidade.repository.ProdutoRepository;
import com.cartaofidelidade.cartaofidelidade.service.ProdutoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    private ProdutoRepository produtoRepository;

    private LojaRepository lojaRepository;

    public ProdutoServiceImpl(ProdutoRepository produtoRepository, LojaRepository lojaRepository){
        this.produtoRepository = produtoRepository;
        this.lojaRepository = lojaRepository;
    }

    @Override
    public Produto cadastrarProduto(Produto produto, Loja loja){
        loja.getProdutos().add(produto);
        return produtoRepository.save(produto);
    }

    @Override
    public void excluirProduto(Long id){
        Optional<Produto> produto = buscarProdutoPorId(id);
        if(produto.isPresent()) {
            Loja loja = produto.get().getLoja();
            loja.getProdutos().remove(produto);
            produtoRepository.deleteById(id);
        }
    }


    @Override
    public List<Produto> listarProdutosdaLoja(Loja loja){
        List<Produto> listaProdutos = loja.getProdutos();
        if(listaProdutos.isEmpty()) {
            throw new RegraNegocioException("A loja não tem produtos cadastrados!");
        }
        return listaProdutos;
    }

    @Override
    public Optional<Produto> buscarProdutoPorId(Long id) {
        Optional<Produto> produto = produtoRepository.findById(id);
        if (produto.isPresent()) {
            return produto;
        } else {
            throw new RegraNegocioException("Produto não encontrado para o id informado!");
        }
    }
    @Override
    public Loja obterLojaDoProduto(Produto produto) {
        if (produto == null) {
            throw new RegraNegocioException("Produto não pode ser nulo.");
        }
        Long lojaId = produto.getLoja().getId();

        Optional<Loja> lojaOptional = lojaRepository.findById(lojaId);

        if (lojaOptional.isPresent()) {
            return lojaOptional.get();
        } else {
            throw new RegraNegocioException("Loja associada ao produto não foi encontrada.");
        }
    }

    public List<Produto> listarProdutos() {
        List<Produto> produtos = produtoRepository.findAll();
        if(produtos.isEmpty()) {
            throw new RegraNegocioException("Não há produtos cadastrados!");
        }
        return produtos;
    }
}
