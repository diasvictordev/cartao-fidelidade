package com.cartaofidelidade.cartaofidelidade.service;

import com.cartaofidelidade.cartaofidelidade.model.Loja;

public interface LojaService {
    Loja cadastrarLoja(Loja loja);

    void validarCnpj(String cnpj);

    Loja autenticar(String cnpj, String senha);
}
