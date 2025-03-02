package com.rafael.locadoradeveiculos.mapper;

import com.rafael.locadoradeveiculos.dto.response.LocacaoResponse;
import com.rafael.locadoradeveiculos.entity.Locacao;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LocacaoMapper {

    @Mapping(target = "usuarioId", source = "usuario.id")
    @Mapping(target = "veiculoId", source = "veiculo.id")
    LocacaoResponse map (Locacao locacao);
    List<LocacaoResponse> map (List<Locacao> locacaoList);
}
