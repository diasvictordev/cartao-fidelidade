package com.cartaofidelidade.cartaofidelidade.repository;

import com.cartaofidelidade.cartaofidelidade.model.Carteira;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarteiraRepository extends JpaRepository<Carteira, Long> {
    List<Carteira> findByClienteId(Long id);
}
