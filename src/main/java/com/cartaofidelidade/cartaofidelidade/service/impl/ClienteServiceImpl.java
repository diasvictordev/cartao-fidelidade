package com.cartaofidelidade.cartaofidelidade.service.impl;

import com.cartaofidelidade.cartaofidelidade.model.Cliente;
import com.cartaofidelidade.cartaofidelidade.repository.ClienteRepository;
import com.cartaofidelidade.cartaofidelidade.service.ClienteService;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImpl implements ClienteService {

        private ClienteRepository clienteRepository;

        public ClienteServiceImpl(ClienteRepository clienteRepository){
            this.clienteRepository = clienteRepository;
        }

        // mudar o void e criar validação de cadastro
        @Override
        public void cadastrarCliente(Cliente cliente){

        }


}
