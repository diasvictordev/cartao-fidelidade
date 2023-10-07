package com.cartaofidelidade.cartaofidelidade.service.impl;

import com.cartaofidelidade.cartaofidelidade.exceptions.RegraNegocioException;
import com.cartaofidelidade.cartaofidelidade.model.Cliente;
import com.cartaofidelidade.cartaofidelidade.repository.ClienteRepository;
import com.cartaofidelidade.cartaofidelidade.service.ClienteService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

        private ClienteRepository clienteRepository;

        public ClienteServiceImpl(ClienteRepository clienteRepository){
            this.clienteRepository = clienteRepository;
        }


        @Override
        public Cliente cadastrarCliente(Cliente cliente){
                validarCpf(cliente.getCpf());
                return clienteRepository.save(cliente);
        }

        @Override
        public void validarCpf(String cpf){
                boolean existe = clienteRepository.existsByCpf(cpf);
                if (existe) {
                        throw new RegraNegocioException("Já existe um usuário cadastrado com esse cpf!");
                }
                if (cpf.equals("00000000000") ||
                        cpf.equals("11111111111") ||
                        cpf.equals("22222222222") || cpf.equals("33333333333") ||
                        cpf.equals("44444444444") || cpf.equals("55555555555") ||
                        cpf.equals("66666666666") || cpf.equals("77777777777") ||
                        cpf.equals("88888888888") || cpf.equals("99999999999") ||
                        (cpf.length() != 11)){
                        throw new RegraNegocioException("CPF inválido, digite novamente!");
                }
        }

        @Override
        public Cliente autenticar(String cpf, String senha) {

                Optional<Cliente> cliente = clienteRepository.findByCpf(cpf);

                if(!cliente.isPresent()) {
                        if (cpf.equals("00000000000") ||
                                cpf.equals("11111111111") ||
                                cpf.equals("22222222222") || cpf.equals("33333333333") ||
                                cpf.equals("44444444444") || cpf.equals("55555555555") ||
                                cpf.equals("66666666666") || cpf.equals("77777777777") ||
                                cpf.equals("88888888888") || cpf.equals("99999999999") ||
                                (cpf.length() != 11)){
                                throw new RegraNegocioException("CPF inválido, digite novamente!");
                        }
                        throw new RegraNegocioException("Cliente não encontrado para o cpf informado. Faça o cadastro!");
                }

                if(!cliente.get().getSenha().equals(senha)) {
                        throw new RegraNegocioException("Senha inválida. Tente novamente!");
                }
                return cliente.get();
        }






}
