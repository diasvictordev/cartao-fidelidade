package com.cartaofidelidade.cartaofidelidade.repository;

import com.cartaofidelidade.cartaofidelidade.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
