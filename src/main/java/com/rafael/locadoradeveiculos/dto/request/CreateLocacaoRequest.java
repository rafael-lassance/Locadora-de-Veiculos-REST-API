package com.rafael.locadoradeveiculos.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public record CreateLocacaoRequest(@Positive @NotNull(message = "Id do veículo é obrigatório") Long veiculoId,
                                   @NotNull(message = "Data de início é obrigatória") @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}") String dataInicio,
                                   @NotNull(message = "Data de fim é obrigatória") @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}") String dataFim) {
}
