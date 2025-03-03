package com.rafael.locadoradeveiculos.dto.response;

import java.util.List;

public record VeiculoResponsePage(long total,
                                  int pages,
                                  List<VeiculoResponse> content) {
}
