package com.rafael.locadoradeveiculos.controller;

import com.rafael.locadoradeveiculos.dto.request.CreateVeiculoRequest;
import com.rafael.locadoradeveiculos.dto.request.UpdateVeiculoRequest;
import com.rafael.locadoradeveiculos.dto.request.VeiculoFilterRequest;
import com.rafael.locadoradeveiculos.dto.response.IdResponse;
import com.rafael.locadoradeveiculos.dto.response.VeiculoResponse;
import com.rafael.locadoradeveiculos.dto.response.VeiculoResponsePage;
import com.rafael.locadoradeveiculos.service.VeiculoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    private final VeiculoService veiculoService;

    @Operation(summary = "Registra um novo veículo", description = "Retorna o id do veículo criado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Veículo criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação")
    })
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<IdResponse> create (@RequestBody @Valid CreateVeiculoRequest createVeiculoRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(veiculoService.create(createVeiculoRequest));
    }

    @Operation(summary = "Lista os atributos de um veículo", description = "Retorna os atributos de um veículo específico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Veículo retornado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Veículo não encontrado")
    })
    @PreAuthorize("hasAnyRole('ADMIN','CLIENTE')")
    @GetMapping("/{id}")
    public ResponseEntity<VeiculoResponse> findById (@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(veiculoService.findById(id));
    }

    @Operation(summary = "Lista todos os veículos filtrados e com Paginação", description = "Realiza uma busca de veículos na base filtrada pelo atributo 'Marca' utilizando o parâmetro passado 'filter' e retorna a página solicitada contendo os veículos encontrados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Veículos retornados com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação")
    })
    @PreAuthorize("hasAnyRole('ADMIN','CLIENTE')")
    @GetMapping
    public ResponseEntity<VeiculoResponsePage> findAllWithPagination (@ParameterObject VeiculoFilterRequest veiculoFilterRequest) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(veiculoService.findAllWithPagination(veiculoFilterRequest));
    }

    @Operation(summary = "Atualiza um veículo", description = "Atualiza um veículo específico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Veículo atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Veículo não encontrado/Erro de validação")
    })
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> update (@PathVariable Long id, @RequestBody @Valid UpdateVeiculoRequest updateVeiculoRequest) {
        veiculoService.update(id, updateVeiculoRequest);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Deleta um veículo", description = "Deleta um veículo específico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Veículo deletado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Veículo não encontrado")
    })
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        veiculoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
