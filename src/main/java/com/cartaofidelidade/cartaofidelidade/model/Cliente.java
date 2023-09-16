package com.cartaofidelidade.cartaofidelidade.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "cliente", schema = "cartao_fidelidade")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {

    @Id
    @Column(name = "cpf")
    private Long cpf;

    @Column(name = "nome")
    private String nome;

    @Column(name = "endereco")
    private String endereco;

    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    @Column(name = "telefone")
    private Integer telefone;

    @Column(name = "senha")
    private String senha;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Produto> premioResgatado = new ArrayList<>();


}
