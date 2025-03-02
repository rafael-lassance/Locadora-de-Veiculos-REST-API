package com.rafael.locadoradeveiculos.service;

import com.rafael.locadoradeveiculos.dto.request.CreateLocacaoRequest;
import com.rafael.locadoradeveiculos.dto.request.UpdateLocacaoRequest;
import com.rafael.locadoradeveiculos.dto.response.IdResponse;
import com.rafael.locadoradeveiculos.dto.response.LocacaoResponse;
import com.rafael.locadoradeveiculos.entity.Locacao;
import com.rafael.locadoradeveiculos.entity.Usuario;
import com.rafael.locadoradeveiculos.entity.Veiculo;
import com.rafael.locadoradeveiculos.entity.enums.StatusLocacao;
import com.rafael.locadoradeveiculos.exception.ValidacaoException;
import com.rafael.locadoradeveiculos.mapper.LocacaoMapper;
import com.rafael.locadoradeveiculos.repository.LocacaoRepository;
import com.rafael.locadoradeveiculos.repository.UsuarioRepository;
import com.rafael.locadoradeveiculos.repository.VeiculoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

@RequiredArgsConstructor
@Service
public class LocacaoService {

    private final double PERCENTUAL_DESCONTO = 0.1;

    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");


    private final LocacaoRepository locacaoRepository;
    private final UsuarioRepository usuarioRepository;
    private final VeiculoRepository veiculoRepository;

    private final LocacaoMapper locacaoMapper;

    public IdResponse create(CreateLocacaoRequest createLocacaoRequest) {

        Usuario usuario;
        Veiculo veiculo;

        var usuarioOptional = usuarioRepository.findById(createLocacaoRequest.usuarioId());
        if(usuarioOptional.isEmpty()){
            throw new ValidacaoException("Usuário não encontrado.");
        }
        usuario = usuarioOptional.get();

        var veiculoOptional = veiculoRepository.findById(createLocacaoRequest.veiculoId());
        if(veiculoOptional.isEmpty()){
            throw new ValidacaoException("Veículo não encontrado.");
        }
        veiculo = veiculoOptional.get();

        LocalDate dataInicio = LocalDate.parse(createLocacaoRequest.dataInicio(), dateTimeFormatter);
        LocalDate dataFim = LocalDate.parse(createLocacaoRequest.dataFim(), dateTimeFormatter);

        long quantidadeDias = ChronoUnit.DAYS.between(dataInicio, dataFim);

        /*
            REGRA DE NEGÓCIO:
            Ao receber uma nova Locação, o sistema deve calcular o valor total da locação com base nas diárias.
            Se o cliente realizar a locação com mais de 1 semana o sistema deve conceder um desconto de 10% no valor final da locação.
        */
        BigDecimal valorDiaria = veiculo.getValorDiaria();
        BigDecimal valorTotal = valorDiaria.multiply(BigDecimal.valueOf(quantidadeDias));
        BigDecimal desconto = quantidadeDias > 7 ? valorTotal.multiply(BigDecimal.valueOf(PERCENTUAL_DESCONTO)) : BigDecimal.ZERO;
        BigDecimal valorFinal = valorTotal.subtract(desconto);

        var locacao = new Locacao();
        locacao.setUsuario(usuario);
        locacao.setVeiculo(veiculo);
        locacao.setDataInicio(dataInicio);
        locacao.setDataFim(dataFim);
        locacao.setQuantidadeDias(quantidadeDias);
        locacao.setValorTotal(valorTotal);
        locacao.setDesconto(desconto);
        locacao.setValorFinal(valorFinal);
        locacao.setStatusLocacao(StatusLocacao.NOVA);

        locacaoRepository.save(locacao);

        return new IdResponse(locacao.getId());

    }

    public LocacaoResponse findById (Long id) {
        var locacaoOptional = locacaoRepository.findById(id);
        if(locacaoOptional.isEmpty()){
            throw new ValidacaoException("Locacao não encontrada");
        }
        return locacaoMapper.map(locacaoOptional.get());
    }

    public List<LocacaoResponse> findAll() {
        return locacaoMapper.map(locacaoRepository.findAll());
    }

    public void update(Long id, UpdateLocacaoRequest updateLocacaoRequest) {

        Locacao locacao;
        Usuario usuario;
        Veiculo veiculo;

        var locacaoOptional = locacaoRepository.findById(id);
        if(locacaoOptional.isEmpty()){
            throw new ValidacaoException("Locação não encontrada.");
        }
        locacao = locacaoOptional.get();

        var usuarioOptional = usuarioRepository.findById(updateLocacaoRequest.usuarioId());
        if(usuarioOptional.isEmpty()){
            throw new ValidacaoException("Usuário não encontrado.");
        }
        usuario = usuarioOptional.get();

        var veiculoOptional = veiculoRepository.findById(updateLocacaoRequest.veiculoId());
        if(veiculoOptional.isEmpty()){
            throw new ValidacaoException("Veículo não encontrado.");
        }
        veiculo = veiculoOptional.get();

        LocalDate dataInicio = LocalDate.parse(updateLocacaoRequest.dataInicio(), dateTimeFormatter);
        LocalDate dataFim = LocalDate.parse(updateLocacaoRequest.dataFim(), dateTimeFormatter);

        long quantidadeDias = ChronoUnit.DAYS.between(dataInicio, dataFim);

        /*
            REGRA DE NEGÓCIO:
            Ao atualizar uma Locação, o sistema deve recalcular o valor total da locação com base nas diárias.
            Se o cliente realizar a locação com mais de 1 semana o sistema deve conceder um desconto de 10% no valor final da locação.
        */
        BigDecimal valorDiaria = veiculo.getValorDiaria();
        BigDecimal valorTotal = valorDiaria.multiply(BigDecimal.valueOf(quantidadeDias));
        BigDecimal desconto = quantidadeDias > 7 ? valorTotal.multiply(BigDecimal.valueOf(PERCENTUAL_DESCONTO)) : BigDecimal.ZERO;
        BigDecimal valorFinal = valorTotal.subtract(desconto);

        locacao.setUsuario(usuario);
        locacao.setVeiculo(veiculo);
        locacao.setDataInicio(dataInicio);
        locacao.setDataFim(dataFim);
        locacao.setQuantidadeDias(quantidadeDias);
        locacao.setValorTotal(valorTotal);
        locacao.setDesconto(desconto);
        locacao.setValorFinal(valorFinal);
        locacao.setStatusLocacao(StatusLocacao.NOVA);

        locacaoRepository.save(locacao);

    }

    public void deleteById(Long id) {
        var locacaoOptional = locacaoRepository.findById(id);
        if(locacaoOptional.isEmpty()){
            throw new ValidacaoException("Locação não encontrada.");
        }

        locacaoRepository.deleteById(id);

    }
}
