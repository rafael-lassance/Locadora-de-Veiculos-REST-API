package com.rafael.locadoradeveiculos.mapper;

import com.rafael.locadoradeveiculos.dto.response.LocacaoResponse;
import com.rafael.locadoradeveiculos.entity.Locacao;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LocacaoMapper {

    LocacaoResponse map (Locacao locacao);
    List<LocacaoResponse> map (List<Locacao> locacaoList);
}
