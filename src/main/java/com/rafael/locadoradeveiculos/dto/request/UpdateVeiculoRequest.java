package com.rafael.locadoradeveiculos.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;

public record UpdateVeiculoRequest(@NotBlank(message = "Marca é obrigatória") @Length(max = 50) String marca,
                                   @NotBlank(message = "Modelo é obrigatório") @Length(max = 50) String modelo,
                                   @Positive @NotNull(message = "Valor da Diária é obrigatório") BigDecimal valorDiaria) {
}