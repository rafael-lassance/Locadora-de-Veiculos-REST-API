package com.rafael.locadoradeveiculos.dto.request;

public record VeiculoFilterRequest(int page,
                                   int size,
                                   String filter) {
}
