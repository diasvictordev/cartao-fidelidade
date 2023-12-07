package com.cartaofidelidade.cartaofidelidade.model;

import com.cartaofidelidade.cartaofidelidade.model.userenums.LojaEnums;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Loja {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name= "id")
    private Long id;

    @Column(name = "cnpj")
    private String cnpj;

    @Column(name = "nome")
    private String nomeLoja;

    @Column(name = "endereco")
    private String endereco;

    @Column(name = "senha")
    private String senha;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Produto> produtos = new ArrayList<>();

    @Column(name = "ativa")
    private Boolean ativa;

    @Column(name = "papel")
    @Enumerated(value= EnumType.STRING)
    private LojaEnums papel;
}
