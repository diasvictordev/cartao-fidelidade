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
public class Venda {

    @Id
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "produto")
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "loja")
    private Loja loja;

    @ManyToOne
    @JoinColumn(name = "cliente")
    private Cliente cliente;

}
