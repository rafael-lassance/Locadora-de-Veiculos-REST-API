package com.rafael.locadoradeveiculos.controller;

import com.rafael.locadoradeveiculos.dto.request.CreateUsuarioRequest;
import com.rafael.locadoradeveiculos.dto.request.UpdateUsuarioRequest;
import com.rafael.locadoradeveiculos.dto.response.IdResponse;
import com.rafael.locadoradeveiculos.dto.response.UsuarioResponse;
import com.rafael.locadoradeveiculos.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<IdResponse> create(@RequestBody @Valid CreateUsuarioRequest createUsuarioRequest){
         return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(usuarioService.create(createUsuarioRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponse> findById(@PathVariable Long id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(usuarioService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponse>> findAll(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(usuarioService.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody @Valid UpdateUsuarioRequest updateUsuarioRequest, @PathVariable Long id){
        usuarioService.update(updateUsuarioRequest, id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        usuarioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
