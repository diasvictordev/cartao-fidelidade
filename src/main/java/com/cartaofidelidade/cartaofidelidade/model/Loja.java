package com.cartaofidelidade.cartaofidelidade.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "loja", schema = "cartao_fidelidade")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Loja {

    @Id
    @Column(name = "cnpj")
    private Long cnpj;

    @Column(name = "nome")
    private String nomeLoja;

    @Column(name = "endereco")
    private String endereco;

    @Column(name = "senha")
    private String senha;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Produto> produtos = new ArrayList<>();
}
