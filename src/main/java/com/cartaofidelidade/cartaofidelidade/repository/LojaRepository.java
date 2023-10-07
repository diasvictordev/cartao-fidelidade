package com.cartaofidelidade.cartaofidelidade.repository;

import com.cartaofidelidade.cartaofidelidade.model.Cliente;
import com.cartaofidelidade.cartaofidelidade.model.Loja;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LojaRepository extends JpaRepository<Loja, Long> {

    boolean existsByCnpj(String cnpj);

    Optional<Loja> findByCnpj(String cnpj);
}
