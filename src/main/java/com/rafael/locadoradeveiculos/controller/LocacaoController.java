package com.rafael.locadoradeveiculos.controller;

import com.rafael.locadoradeveiculos.dto.request.CreateLocacaoRequest;
import com.rafael.locadoradeveiculos.dto.response.IdResponse;
import com.rafael.locadoradeveiculos.service.LocacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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



}
