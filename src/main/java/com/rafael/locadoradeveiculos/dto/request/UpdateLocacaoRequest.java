package com.rafael.locadoradeveiculos.dto.request;

public record UpdateLocacaoRequest(Long usuarioId,
                                   Long veiculoId,
                                   String dataInicio,
                                   String dataFim) {
}
