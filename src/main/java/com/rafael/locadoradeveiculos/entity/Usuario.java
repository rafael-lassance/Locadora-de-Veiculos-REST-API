package com.rafael.locadoradeveiculos.entity;

import com.rafael.locadoradeveiculos.entity.enums.TipoUsuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(length = 50, nullable = false)
    @Length(max=50)
    private String nome;

    @CPF
    @Column(length = 14, nullable = false)
    private String cpf;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Length(min=8, max=20)
    private String senha;

    @Enumerated(EnumType.STRING)
    private TipoUsuario tipoUsuario;

}
