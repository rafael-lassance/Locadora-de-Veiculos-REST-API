package com.rafael.locadoradeveiculos.entity;

import com.rafael.locadoradeveiculos.entity.enums.StatusLocacao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "locacoes")
public class Locacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Usuario usuario;

    @ManyToOne
    private Veiculo veiculo;

    private LocalDate dataInicio;

    private LocalDate dataFim;

    private Long quantidadeDias;

    private BigDecimal valorTotal;

    private BigDecimal desconto;

    private BigDecimal valorFinal;

    @Enumerated(EnumType.STRING)
    private StatusLocacao statusLocacao;

}
