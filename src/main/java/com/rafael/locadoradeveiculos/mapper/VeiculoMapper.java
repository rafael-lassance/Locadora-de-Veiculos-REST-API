package com.rafael.locadoradeveiculos.mapper;

import com.rafael.locadoradeveiculos.dto.request.CreateVeiculoRequest;
import com.rafael.locadoradeveiculos.dto.response.VeiculoResponse;
import com.rafael.locadoradeveiculos.entity.Veiculo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VeiculoMapper {

    @Mapping(target = "tipoMotor", constant = "MOTOR_A_GASOLINA")
    Veiculo map(CreateVeiculoRequest createVeiculoRequest);
    VeiculoResponse map(Veiculo veiculo);
    List<VeiculoResponse> map(List<Veiculo> veiculoList);
}
