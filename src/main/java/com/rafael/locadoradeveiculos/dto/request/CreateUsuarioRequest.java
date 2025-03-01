package com.rafael.locadoradeveiculos.dto.request;

public record CreateUsuarioRequest( String nome,
                                    String cpf,
                                    String email,
                                    String senha
) {
}
