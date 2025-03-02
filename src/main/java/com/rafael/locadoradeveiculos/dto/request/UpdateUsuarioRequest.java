package com.rafael.locadoradeveiculos.dto.request;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

public record UpdateUsuarioRequest(@NotBlank(message = "Nome é obrigatório")  @Length(max=50) String nome,
                                   @NotBlank(message = "CPF é obrigatório") @CPF String cpf
) {
}
