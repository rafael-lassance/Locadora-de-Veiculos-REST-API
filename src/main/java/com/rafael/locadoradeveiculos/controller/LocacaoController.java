package com.rafael.locadoradeveiculos.controller;

import com.rafael.locadoradeveiculos.dto.request.CreateLocacaoRequest;
import com.rafael.locadoradeveiculos.dto.request.UpdateLocacaoRequest;
import com.rafael.locadoradeveiculos.dto.response.IdResponse;
import com.rafael.locadoradeveiculos.dto.response.LocacaoResponse;
import com.rafael.locadoradeveiculos.service.LocacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/locacoes")
public class LocacaoController {

    private final LocacaoService locacaoService;

    @Operation(summary = "Registra uma nova locação", description = "Retorna o id da locação criada")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Locação criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação")
    })
    @PreAuthorize("hasRole('CLIENTE')")
    @PostMapping
    public ResponseEntity<IdResponse> create (@RequestBody @Valid CreateLocacaoRequest createLocacaoRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(locacaoService.create(createLocacaoRequest));
    }

    @Operation(summary = "Lista os atributos de uma locação", description = "Retorna os atributos de uma locação específica")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Locação retornada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Locação não encontrada")
    })
    @PreAuthorize("hasRole('CLIENTE')")
    @GetMapping("/{id}")
    public ResponseEntity<LocacaoResponse> findById (@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(locacaoService.findById(id));
    }

    @Operation(summary = "Lista todos as locações", description = "Retorna a lista de todas as locações na base")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Locações retornadas com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação")
    })
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<LocacaoResponse>> findAll () {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(locacaoService.findAll());
    }

    @Operation(summary = "Atualiza uma locação", description = "Atualiza uma locação específica")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Locação atualizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Locação não encontrada/Erro de validação")
    })
    @PreAuthorize("hasRole('CLIENTE')")
    @PutMapping("/{id}")
    public ResponseEntity<?> update (@PathVariable Long id, @RequestBody @Valid UpdateLocacaoRequest updateLocacaoRequest) {
        locacaoService.update(id, updateLocacaoRequest);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Deleta uma locação", description = "Deleta uma locação específica")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Locação deletada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Locação não encontrada")
    })
    @PreAuthorize("hasRole('CLIENTE')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById (@PathVariable Long id) {
       locacaoService.deleteById(id);
       return ResponseEntity.noContent().build();
    }



}
