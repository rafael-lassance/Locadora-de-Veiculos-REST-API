package com.rafael.locadoradeveiculos.dto.request;

import java.time.LocalDate;

public record CreateLocacaoRequest(Long usuarioId,
                                   Long veiculoId,
                                   String dataInicio,
                                   String dataFim) {
}
