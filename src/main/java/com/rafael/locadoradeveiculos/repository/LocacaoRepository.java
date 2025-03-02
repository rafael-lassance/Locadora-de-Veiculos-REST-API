package com.rafael.locadoradeveiculos.repository;

import com.rafael.locadoradeveiculos.entity.Locacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocacaoRepository extends JpaRepository<Locacao, Long> {
}
