package com.rafael.locadoradeveiculos.repository;

import com.rafael.locadoradeveiculos.entity.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
}
