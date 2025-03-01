package com.rafael.locadoradeveiculos.dto.response;

public record UsuarioResponse(Long id,
                              String nome,
                              String cpf,
                              String email,
                              String senha
) {
}
