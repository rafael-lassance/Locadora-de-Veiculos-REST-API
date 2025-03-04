package com.rafael.locadoradeveiculos.repository;

import com.rafael.locadoradeveiculos.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByEmail(String email);
}
