package com.rafael.locadoradeveiculos.dto.request;

import java.math.BigDecimal;

public record UpdateVeiculoRequest(String marca,
                                   String modelo,
                                   BigDecimal valorDiaria) {
}
