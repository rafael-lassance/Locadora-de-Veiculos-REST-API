package com.rafael.locadoradeveiculos.repository;

import com.rafael.locadoradeveiculos.entity.Veiculo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {

    Page<Veiculo> findAllByMarcaContainsIgnoreCase(String filtro, Pageable pageable);
    @Query("select v from Veiculo v where upper(v.marca) like (%?1%)")
    Page<Veiculo> findVeiculos(String filtro, Pageable pageable);
}
