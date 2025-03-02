package com.rafael.locadoradeveiculos.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

public record CreateUsuarioRequest(@NotBlank(message = "Nome é obrigatório")  @Length(max=50) String nome,
                                   @NotBlank(message = "CPF é obrigatório") @CPF String cpf,
                                   @NotBlank(message = "E-mail é obrigatório") @Email String email,
                                   @NotBlank(message = "Senha é obrigatório") @Length(min=8, max=20) String senha
) {
}
