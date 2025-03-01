package com.rafael.locadoradeveiculos.mapper;


import com.rafael.locadoradeveiculos.dto.request.CreateUsuarioRequest;
import com.rafael.locadoradeveiculos.dto.response.UsuarioResponse;
import com.rafael.locadoradeveiculos.entity.Usuario;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    @Mapping(target = "tipoUsuario", constant = "CLIENTE")
    Usuario map(CreateUsuarioRequest createUsuarioRequest);

    UsuarioResponse map(Usuario usuario);

    List<UsuarioResponse> map(List<Usuario> usuarioList);


}
