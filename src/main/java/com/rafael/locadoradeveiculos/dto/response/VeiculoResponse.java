package com.rafael.locadoradeveiculos.dto.response;

import java.math.BigDecimal;

public record VeiculoResponse(Long id,
                              String marca,
                              String modelo,
                              BigDecimal valorDiaria,
                              String tipoMotor) {
}
