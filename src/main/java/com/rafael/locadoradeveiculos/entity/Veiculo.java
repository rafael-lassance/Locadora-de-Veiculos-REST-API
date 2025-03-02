package com.rafael.locadoradeveiculos.entity;

import com.rafael.locadoradeveiculos.entity.enums.TipoMotor;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

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

    @NotBlank
    @Length(max = 50)
    @Column(length = 50)
    private String marca;


    @NotBlank
    @Length(max = 50)
    @Column(length = 50)
    private String modelo;

    @Positive
    @NotNull
    private BigDecimal valorDiaria;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TipoMotor tipoMotor;
}
