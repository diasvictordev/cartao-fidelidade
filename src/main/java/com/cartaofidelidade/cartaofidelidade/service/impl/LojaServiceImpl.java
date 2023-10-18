package com.cartaofidelidade.cartaofidelidade.service.impl;

import com.cartaofidelidade.cartaofidelidade.exceptions.RegraNegocioException;
import com.cartaofidelidade.cartaofidelidade.model.Carteira;
import com.cartaofidelidade.cartaofidelidade.model.Cliente;
import com.cartaofidelidade.cartaofidelidade.model.Loja;
import com.cartaofidelidade.cartaofidelidade.repository.CarteiraRepository;
import com.cartaofidelidade.cartaofidelidade.repository.LojaRepository;
import com.cartaofidelidade.cartaofidelidade.service.LojaService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LojaServiceImpl implements LojaService {

    private LojaRepository lojaRepository;

    private CarteiraRepository carteiraRepository;

    public LojaServiceImpl(LojaRepository lojaRepository, CarteiraRepository carteiraRepository){
        this.lojaRepository = lojaRepository;
        this.carteiraRepository = carteiraRepository;
    }


    @Override
    public Loja cadastrarLoja(Loja loja){
        validarCnpj(loja.getCnpj());
        return lojaRepository.save(loja);
    }



    @Override
    public void validarCnpj(String cnpj){
        boolean existe = lojaRepository.existsByCnpj(cnpj);
        if (existe) {
            throw new RegraNegocioException("Já existe uma loja com esse CNPJ!");
        }
        if (cnpj.equals("00000000000000") ||
                cnpj.equals("11111111111111") ||
                cnpj.equals("22222222222222") || cnpj.equals("33333333333333") ||
                cnpj.equals("44444444444444") || cnpj.equals("55555555555555") ||
                cnpj.equals("66666666666666") || cnpj.equals("77777777777777") ||
                cnpj.equals("88888888888888") || cnpj.equals("99999999999999") ||
                (cnpj.length() != 14)){
            throw new RegraNegocioException("CNPJ inválido, digite novamente!");
        }
    }

    @Override
    public Loja autenticar(String cnpj, String senha) {

        Optional<Loja> loja = lojaRepository.findByCnpj(cnpj);

        if(!loja.isPresent()) {
            if (cnpj.equals("00000000000000") ||
                    cnpj.equals("11111111111111") ||
                    cnpj.equals("22222222222222") || cnpj.equals("33333333333333") ||
                    cnpj.equals("44444444444444") || cnpj.equals("55555555555555") ||
                    cnpj.equals("66666666666666") || cnpj.equals("77777777777777") ||
                    cnpj.equals("88888888888888") || cnpj.equals("99999999999999") ||
                    (cnpj.length() != 14)){
                throw new RegraNegocioException("CNPJ inválido, digite novamente!");
            }
            throw new RegraNegocioException("Loja não encontrada para o cnpj informado. Faça o cadastro!");
        }

        if(!loja.get().getSenha().equals(senha)) {
            throw new RegraNegocioException("Senha inválida. Tente novamente!");
        }
        return loja.get();
    }

    @Override
    public Carteira criarCarteira(Cliente cliente, Loja loja, Carteira carteira){
        carteira.setLoja(loja);
        carteira.setCliente(cliente);
        return carteiraRepository.save(carteira);
    }


}
