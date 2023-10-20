package com.cartaofidelidade.cartaofidelidade.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "produto", schema = "cartao_fidelidade")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome")
    private String nomeProduto;

    @Column(name = "valor")
    private Float valor;

    @Column(name = "descricao")
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "loja_id")
    private Loja loja;
}
