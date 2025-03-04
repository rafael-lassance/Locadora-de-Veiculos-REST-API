package com.rafael.locadoradeveiculos.controller;

import com.rafael.locadoradeveiculos.dto.request.CreateVeiculoRequest;
import com.rafael.locadoradeveiculos.dto.request.UpdateVeiculoRequest;
import com.rafael.locadoradeveiculos.dto.request.VeiculoFilterRequest;
import com.rafael.locadoradeveiculos.dto.response.IdResponse;
import com.rafael.locadoradeveiculos.dto.response.VeiculoResponse;
import com.rafael.locadoradeveiculos.dto.response.VeiculoResponsePage;
import com.rafael.locadoradeveiculos.service.VeiculoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    private final VeiculoService veiculoService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<IdResponse> create (@RequestBody @Valid CreateVeiculoRequest createVeiculoRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(veiculoService.create(createVeiculoRequest));
    }

    @PreAuthorize("hasAnyRole('ADMIN','CLIENTE')")
    @GetMapping("/{id}")
    public ResponseEntity<VeiculoResponse> findById (@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(veiculoService.findById(id));
    }

    @PreAuthorize("hasAnyRole('ADMIN','CLIENTE')")
    @GetMapping
    public ResponseEntity<VeiculoResponsePage> findAllWithPagination (VeiculoFilterRequest veiculoFilterRequest) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(veiculoService.findAllWithPagination(veiculoFilterRequest));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> update (@PathVariable Long id, @RequestBody @Valid UpdateVeiculoRequest updateVeiculoRequest) {
        veiculoService.update(id, updateVeiculoRequest);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        veiculoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
