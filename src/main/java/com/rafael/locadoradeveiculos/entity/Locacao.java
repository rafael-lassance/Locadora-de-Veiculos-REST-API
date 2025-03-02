package com.rafael.locadoradeveiculos.entity;

import com.rafael.locadoradeveiculos.entity.enums.StatusLocacao;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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

    @NotNull
    private LocalDate dataInicio;

    @NotNull
    private LocalDate dataFim;

    @NotNull
    @Positive
    private Long quantidadeDias;

    @NotNull
    @Positive
    private BigDecimal valorTotal;

    @NotNull
    private BigDecimal desconto;

    @NotNull
    @Positive
    private BigDecimal valorFinal;

    @NotNull
    @Enumerated(EnumType.STRING)
    private StatusLocacao statusLocacao;

}
