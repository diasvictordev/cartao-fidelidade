package com.cartaofidelidade.cartaofidelidade.service;

import com.cartaofidelidade.cartaofidelidade.model.Carteira;
import com.cartaofidelidade.cartaofidelidade.model.Cliente;
import com.cartaofidelidade.cartaofidelidade.model.Loja;

import java.util.List;
import java.util.Optional;

public interface LojaService {
    Loja cadastrarLoja(Loja loja);

    List<Loja> listarLojas();

    void validarCnpj(String cnpj);

    Loja autenticar(String cnpj, String senha);

    Carteira criarCarteira(Cliente cliente, Loja loja, Carteira carteira);

    Optional<Carteira> procurarCarteiraporId(Long id);

    void adicionarPontosNaCarteira(Long id, Integer pontos);
}
