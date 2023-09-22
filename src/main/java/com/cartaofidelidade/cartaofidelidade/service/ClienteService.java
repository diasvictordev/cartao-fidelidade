package com.cartaofidelidade.cartaofidelidade.service;

import com.cartaofidelidade.cartaofidelidade.model.Cliente;

public interface ClienteService {
    // mudar o void e criar validação de cadastro
    Cliente cadastrarCliente(Cliente cliente);

    void validarCpf(String cpf);

    Cliente autenticar(String cpf, String senha);
}
