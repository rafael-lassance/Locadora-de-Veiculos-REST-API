package com.rafael.locadoradeveiculos.service;

import com.rafael.locadoradeveiculos.dto.request.CreateVeiculoRequest;
import com.rafael.locadoradeveiculos.dto.request.UpdateVeiculoRequest;
import com.rafael.locadoradeveiculos.dto.response.IdResponse;
import com.rafael.locadoradeveiculos.dto.response.VeiculoResponse;
import com.rafael.locadoradeveiculos.exception.ValidacaoException;
import com.rafael.locadoradeveiculos.mapper.VeiculoMapper;
import com.rafael.locadoradeveiculos.repository.VeiculoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class VeiculoService {

    private final VeiculoRepository veiculoRepository;

    private final VeiculoMapper veiculoMapper;


    public IdResponse create(CreateVeiculoRequest createVeiculoRequest) {
        var veiculo = veiculoMapper.map(createVeiculoRequest);
        veiculoRepository.save(veiculo);
        return new IdResponse(veiculo.getId());
    }

    public VeiculoResponse findById(Long id) {
        var veiculoOptional = veiculoRepository.findById(id);
        if(veiculoOptional.isEmpty()){
            throw new ValidacaoException("Veículo não encontrado.");
        }
        return veiculoMapper.map(veiculoOptional.get());
    }

    public List<VeiculoResponse> findAll() {
        return veiculoMapper.map(veiculoRepository.findAll());
    }

    public void update(Long id, UpdateVeiculoRequest updateVeiculoRequest) {
        var veiculoOptional = veiculoRepository.findById(id);
        if(veiculoOptional.isEmpty()){
            throw new ValidacaoException("Veículo não encontrado.");
        }

        var veiculo = veiculoOptional.get();
        veiculo.setMarca(updateVeiculoRequest.marca());
        veiculo.setModelo(updateVeiculoRequest.modelo());
        veiculo.setValorDiaria(updateVeiculoRequest.valorDiaria());

        veiculoRepository.save(veiculo);
    }


    public void deleteById(Long id) {
        var veiculoOptional = veiculoRepository.findById(id);
        if(veiculoOptional.isEmpty()){
            throw new ValidacaoException("Veículo não encontrado.");
        }

        veiculoRepository.deleteById(id);
    }
}
