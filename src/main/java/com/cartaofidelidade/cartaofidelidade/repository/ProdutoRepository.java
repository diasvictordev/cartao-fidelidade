package com.cartaofidelidade.cartaofidelidade.repository;

import com.cartaofidelidade.cartaofidelidade.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
