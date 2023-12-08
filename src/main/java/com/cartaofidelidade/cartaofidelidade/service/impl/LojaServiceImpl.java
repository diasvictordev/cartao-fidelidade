package com.cartaofidelidade.cartaofidelidade.service.impl;

import com.cartaofidelidade.cartaofidelidade.exceptions.RegraNegocioException;
import com.cartaofidelidade.cartaofidelidade.model.Carteira;
import com.cartaofidelidade.cartaofidelidade.model.Cliente;
import com.cartaofidelidade.cartaofidelidade.model.Loja;
import com.cartaofidelidade.cartaofidelidade.model.userenums.LojaEnums;
import com.cartaofidelidade.cartaofidelidade.repository.CarteiraRepository;
import com.cartaofidelidade.cartaofidelidade.repository.ClienteRepository;
import com.cartaofidelidade.cartaofidelidade.repository.LojaRepository;
import com.cartaofidelidade.cartaofidelidade.service.LojaService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LojaServiceImpl implements LojaService {

    private LojaRepository lojaRepository;

    private CarteiraRepository carteiraRepository;

    private ClienteRepository clienteRepository;

    public LojaServiceImpl(LojaRepository lojaRepository, CarteiraRepository carteiraRepository, ClienteRepository clienteRepository){
        this.lojaRepository = lojaRepository;
        this.carteiraRepository = carteiraRepository;
        this.clienteRepository = clienteRepository;
    }


    @Override
    public Loja cadastrarLoja(Loja loja){
        validarCnpj(loja.getCnpj());
        loja.setAtiva(true);
        return lojaRepository.save(loja);
    }

    @Override
    public void desativarLoja(Loja loja){
        loja.setAtiva(false);
    }

    @Override
    public void ativarLoja(Loja loja){
        loja.setAtiva(true);
    }

    @Override
    public List<Loja> listarLojas(){
        List<Loja> lojas = lojaRepository.findAll();
        if(!lojas.isEmpty()){
            return lojas;
        }
        else{
            throw new RegraNegocioException("Nenhuma loja cadastrada!");
        }
    }

    @Override
    public Optional<Loja> listarLojaPorId(Long id){
        Optional<Loja> loja= lojaRepository.findById(id);
        if(loja.isPresent()){
            return loja;
        }
        else {
            throw new RegraNegocioException("A loja não foi encontrada para o id informado!");
        }
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
    public void excluirLoja(Long id){
        lojaRepository.deleteById(id);
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
    public Loja autenticarAdmin(String cnpj, String senha, LojaEnums papel) {

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
        if(!loja.get().getPapel().equals(LojaEnums.ADMIN)){
            throw new RegraNegocioException("Você não é admin!");
        }
        return loja.get();
    }

    @Override
    public Carteira criarCarteira(Integer quantidadePontos, Long clienteId, Long lojaId) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RegraNegocioException("Cliente não encontrado"));

        Loja loja = lojaRepository.findById(lojaId)
                .orElseThrow(() -> new RegraNegocioException("Loja não encontrada"));

        Carteira carteira = new Carteira();
        carteira.setCliente(cliente);
        carteira.setLoja(loja);
        carteira.setQuantidadePontos(quantidadePontos);

        return carteiraRepository.save(carteira);
    }

    @Override
    public Carteira procurarCarteiraporId(Long id) {
        return carteiraRepository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Carteira não encontrada para o ID: " + id));
    }

    @Override
    public Integer buscarQuantidadePontosCarteiraDoUsuario(Long idCliente) {
        Carteira carteira = procurarCarteiraporIdDoUsuario(idCliente);
        return carteira.getQuantidadePontos();
    }

    @Override
    public Carteira procurarCarteiraporIdDoUsuario(Long id) {
        List<Carteira> carteiras = carteiraRepository.findAll(); // Supondo que seja possível buscar todas as carteiras
        for (Carteira carteira : carteiras) {
            if (carteira.getCliente().getId().equals(id)) {
                return carteira;
            }
        }
        throw new RegraNegocioException("Carteira não encontrada para o cliente com ID: " + id);
    }

    @Override
    public void mudarPontosNaCarteira(Long id, Integer pontos){
        Carteira carteira = procurarCarteiraporId(id);
        Integer pontosExistentes = carteira.getQuantidadePontos();
        carteira.setQuantidadePontos(pontosExistentes + pontos);
    }

    @Override
    public List<Carteira> listarCarteiras(){
        List<Carteira> carteiras = carteiraRepository.findAll();
        if(!carteiras.isEmpty()){
            return carteiras;
        }
        else{
            throw new RegraNegocioException("Nenhuma carteira cadastrada!");
        }
    }

    @Override
    public Optional<Loja> getLojaByCnpj(String cnpj) {
        Optional<Loja> loja = lojaRepository.findByCnpj(cnpj);
        if(loja.isPresent()){
            return loja;
        }
        else {
            throw new RegraNegocioException("A loja não foi encontrada para o cnpj informado!");
        }
    }
}
