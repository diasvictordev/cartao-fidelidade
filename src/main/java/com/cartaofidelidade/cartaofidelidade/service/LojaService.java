package com.cartaofidelidade.cartaofidelidade.service;

import com.cartaofidelidade.cartaofidelidade.model.Carteira;
import com.cartaofidelidade.cartaofidelidade.model.Cliente;
import com.cartaofidelidade.cartaofidelidade.model.Loja;

import java.util.List;
import java.util.Optional;

public interface LojaService {
    Loja cadastrarLoja(Loja loja);

    void desativarLoja(Loja loja);

    void ativarLoja(Loja loja);

    List<Loja> listarLojas();

    Optional<Loja> listarLojaPorId(Long id);

    void validarCnpj(String cnpj);

    void excluirLoja(Long id);

    Loja autenticar(String cnpj, String senha);


    Carteira criarCarteira(Integer quantidadePontos, Long clienteId, Long lojaId);

    Carteira procurarCarteiraporId(Long id);

    Integer buscarQuantidadePontosCarteiraDoUsuario(Long idCliente);

    Carteira procurarCarteiraporIdDoUsuario(Long id);

    void mudarPontosNaCarteira(Long id, Integer pontos);

    List<Carteira> listarCarteiras();
}
