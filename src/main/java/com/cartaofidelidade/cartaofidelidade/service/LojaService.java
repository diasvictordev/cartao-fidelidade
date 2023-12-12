package com.cartaofidelidade.cartaofidelidade.service;

import com.cartaofidelidade.cartaofidelidade.model.Carteira;
import com.cartaofidelidade.cartaofidelidade.model.Cliente;
import com.cartaofidelidade.cartaofidelidade.model.Loja;
import com.cartaofidelidade.cartaofidelidade.model.userenums.LojaEnums;

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

    Loja autenticarAdmin(String cnpj, String senha, LojaEnums papel);

    Carteira criarCarteira(Carteira carteira);

    Carteira procurarCarteiraporId(Long id);

    Integer buscarQuantidadePontosCarteiraDoUsuario(Long idCliente);

    Carteira procurarCarteiraporIdDoUsuario(Long id);

    void mudarPontosNaCarteira(Carteira carteira, Integer pontos);

    List<Carteira> listarCarteiras();

    Optional<Loja> getLojaByCnpj(String cnpj);

    void deletarCarteira(Long id);

    List<Carteira> buscarCarteirasPorCpf(String cpf);
}
