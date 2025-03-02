package com.rafael.locadoradeveiculos.controller;

import com.rafael.locadoradeveiculos.dto.request.CreateLocacaoRequest;
import com.rafael.locadoradeveiculos.dto.request.UpdateLocacaoRequest;
import com.rafael.locadoradeveiculos.dto.response.IdResponse;
import com.rafael.locadoradeveiculos.dto.response.LocacaoResponse;
import com.rafael.locadoradeveiculos.service.LocacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/locacoes")
public class LocacaoController {

    private final LocacaoService locacaoService;

    @PostMapping
    public ResponseEntity<IdResponse> create (@RequestBody CreateLocacaoRequest createLocacaoRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(locacaoService.create(createLocacaoRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LocacaoResponse> findById (@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(locacaoService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<LocacaoResponse>> findAll () {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(locacaoService.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update (@PathVariable Long id, @RequestBody UpdateLocacaoRequest updateLocacaoRequest) {
        locacaoService.update(id, updateLocacaoRequest);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById (@PathVariable Long id) {
       locacaoService.deleteById(id);
       return ResponseEntity.noContent().build();
    }



}
