package com.cartaofidelidade.cartaofidelidade.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "carteira", schema = "cartao_fidelidade")
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
    @JoinColumn(name = "cnpj")
    private Loja loja;

    @ManyToOne
    @JoinColumn(name = "cpf")
    private Cliente cliente;
}
