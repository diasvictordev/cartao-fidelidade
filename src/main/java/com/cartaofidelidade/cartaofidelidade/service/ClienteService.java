package com.cartaofidelidade.cartaofidelidade.service;

import com.cartaofidelidade.cartaofidelidade.model.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteService {
    // mudar o void e criar validação de cadastro
    Cliente cadastrarCliente(Cliente cliente);

    List<Cliente> listarClientes();

    void excluirConta(Long id);

    void validarCpf(String cpf);

    Cliente autenticar(String cpf, String senha);

    Optional<Cliente> buscarClienteporId(Long id);
}
