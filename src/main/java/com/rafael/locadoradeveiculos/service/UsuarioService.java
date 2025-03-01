package com.rafael.locadoradeveiculos.service;

import com.rafael.locadoradeveiculos.dto.request.CreateUsuarioRequest;
import com.rafael.locadoradeveiculos.dto.request.UpdateUsuarioRequest;
import com.rafael.locadoradeveiculos.dto.response.IdResponse;
import com.rafael.locadoradeveiculos.dto.response.UsuarioResponse;
import com.rafael.locadoradeveiculos.exception.ValidacaoException;
import com.rafael.locadoradeveiculos.mapper.UsuarioMapper;
import com.rafael.locadoradeveiculos.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    private final UsuarioMapper usuarioMapper;

    public IdResponse create(CreateUsuarioRequest createUsuarioRequest) {
        var usuario = usuarioMapper.map(createUsuarioRequest);
        usuarioRepository.save(usuario);
        return new IdResponse(usuario.getId());
    }

    public UsuarioResponse findById(Long id) {
        var usuarioOptional = usuarioRepository.findById(id);
        if (usuarioOptional.isEmpty()) {
            throw new ValidacaoException("Usuário não encontrado");
        }

        return usuarioMapper.map(usuarioOptional.get());
    }

    public List<UsuarioResponse> findAll() {
        return usuarioMapper.map(usuarioRepository.findAll());
    }

    public void update(UpdateUsuarioRequest updateUsuarioRequest, Long id) {
        var usuarioOptional = usuarioRepository.findById(id);
        if (usuarioOptional.isEmpty()) {
            throw new ValidacaoException("Usuário não encontrado");
        }
        var usuario = usuarioOptional.get();
        usuario.setNome(updateUsuarioRequest.nome());
        usuario.setCpf(updateUsuarioRequest.cpf());

        usuarioRepository.save(usuario);
    }

    public void deleteById(Long id) {
        var usuarioOptional = usuarioRepository.findById(id);
        if (usuarioOptional.isEmpty()) {
            throw new ValidacaoException("Usuário não encontrado");
        }
        usuarioRepository.deleteById(id);
    }
}