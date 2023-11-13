package com.cartaofidelidade.cartaofidelidade.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Carteira {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "quantidade_pontos")
    private Integer quantidadePontos;

    @ManyToOne
    @JoinColumn(name = "loja")
    private Loja loja;

    @ManyToOne
    @JoinColumn(name = "cliente")
    private Cliente cliente;
}
