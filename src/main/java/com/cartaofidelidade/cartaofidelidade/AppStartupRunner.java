package com.cartaofidelidade.cartaofidelidade;

import com.cartaofidelidade.cartaofidelidade.model.Cliente;
import com.cartaofidelidade.cartaofidelidade.model.Loja;
import com.cartaofidelidade.cartaofidelidade.model.Produto;
import com.cartaofidelidade.cartaofidelidade.repository.ClienteRepository;
import com.cartaofidelidade.cartaofidelidade.repository.LojaRepository;
import com.cartaofidelidade.cartaofidelidade.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;

@Component
public class AppStartupRunner implements ApplicationRunner {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private LojaRepository lojaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        saveCliente();
        saveLoja();

        saveProdutos();
    }

    private void saveProdutos() {
        Produto produto = new Produto();
        produto.setNomeProduto("Produto 1");
        produto.setValor(10.0f);
        produto.setDescricao("Descrição 1");
        produto.setLoja(lojaRepository.findById(1L).get());

        produtoRepository.save(produto);

        produto.setNomeProduto("Produto 2");
        produto.setValor(20.0f);
        produto.setDescricao("Descrição 2");
        produto.setLoja(lojaRepository.findById(1L).get());

        produtoRepository.save(produto);
    }

    private void saveLoja() {
        Loja loja = new Loja();
        loja.setCnpj("12345678910");
        loja.setNomeLoja("Loja 1");
        loja.setEndereco("Rua 1");
        loja.setSenha("1234");

        lojaRepository.save(loja);
    }

    private void saveCliente() {
        Cliente cliente = new Cliente();
        cliente.setCpf("12345678910123");
        cliente.setNome("João");
        cliente.setEndereco("Rua 1");
        cliente.setDataNascimento(LocalDate.of(2000, 1, 1));
        cliente.setTelefone(123456789L);
        cliente.setSenha("1234");
        cliente.setPremioResgatado(new ArrayList<>());

        clienteRepository.save(cliente);
    }


}
