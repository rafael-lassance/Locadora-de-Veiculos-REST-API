package com.rafael.locadoradeveiculos.controller;

import com.rafael.locadoradeveiculos.dto.request.CreateVeiculoRequest;
import com.rafael.locadoradeveiculos.dto.request.UpdateVeiculoRequest;
import com.rafael.locadoradeveiculos.dto.response.IdResponse;
import com.rafael.locadoradeveiculos.dto.response.VeiculoResponse;
import com.rafael.locadoradeveiculos.service.VeiculoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    private final VeiculoService veiculoService;

    @PostMapping
    public ResponseEntity<IdResponse> create (@RequestBody CreateVeiculoRequest createVeiculoRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(veiculoService.create(createVeiculoRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<VeiculoResponse> findById (@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(veiculoService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<VeiculoResponse>> findAll () {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(veiculoService.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update (@PathVariable Long id, @RequestBody UpdateVeiculoRequest updateVeiculoRequest) {
        veiculoService.update(id, updateVeiculoRequest);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        veiculoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
