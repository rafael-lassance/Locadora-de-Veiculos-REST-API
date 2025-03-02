package com.rafael.locadoradeveiculos.dto.request;

import java.math.BigDecimal;

public record CreateVeiculoRequest(String marca,
                                   String modelo,
                                   BigDecimal valorDiaria) {
}
