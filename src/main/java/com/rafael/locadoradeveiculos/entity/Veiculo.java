package com.rafael.locadoradeveiculos.entity;

import com.rafael.locadoradeveiculos.entity.enums.TipoMotor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "veiculos")
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String marca;
    private String modelo;
    private BigDecimal valorDiaria;

    @Enumerated(EnumType.STRING)
    private TipoMotor tipoMotor;
}
