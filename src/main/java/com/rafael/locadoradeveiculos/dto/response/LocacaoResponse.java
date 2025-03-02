package com.rafael.locadoradeveiculos.dto.response;

import java.math.BigDecimal;

public record LocacaoResponse(Long id,
                              Long usuarioId,
                              Long veiculoId,
                              String dataInicio,
                              String dataFim,
                              Long quantidadeDias,
                              BigDecimal valorTotal,
                              BigDecimal desconto,
                              BigDecimal valorFinal,
                              String statusLocacao) {
}
