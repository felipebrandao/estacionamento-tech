package com.fiap.techchallenge.multa.services.impl;

import com.fiap.techchallenge.multa.dtos.MultaDTO;
import com.fiap.techchallenge.multa.exceptions.EstacionamentoTechException;
import com.fiap.techchallenge.multa.feign.EstacionamentoFeignClient;
import com.fiap.techchallenge.multa.feign.dto.VeiculoEstacionadoDTO;
import com.fiap.techchallenge.multa.mappers.MultaMapper;
import com.fiap.techchallenge.multa.model.Multa;
import com.fiap.techchallenge.multa.model.VeiculoEstacionado;
import com.fiap.techchallenge.multa.repositories.MultaRepository;
import com.fiap.techchallenge.multa.services.MultaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class MultaServiceImpl implements MultaService {

    private final MultaRepository multaRepository;
    private final MultaMapper multaMapper;
    private final EstacionamentoFeignClient estacionamentoFeignClient;

    @Autowired
    public MultaServiceImpl(MultaRepository multaRepository, MultaMapper multaMapper, EstacionamentoFeignClient estacionamentoFeignClient) {
        this.multaRepository = multaRepository;
        this.multaMapper = multaMapper;
        this.estacionamentoFeignClient = estacionamentoFeignClient;
    }

    @Override
    public MultaDTO registrarMulta(MultaDTO multaDTO, UUID idUsuario) {

        VeiculoEstacionadoDTO buscaVeiculoEstacionado = estacionamentoFeignClient.veiculoComEstacionamentoAtivo(multaDTO.getIdVeiculo());

        if(buscaVeiculoEstacionado != null && buscaVeiculoEstacionado.getIdLocalEstacionamento().equals(multaDTO.getIdLocalEstacionamento())){;
            throw new EstacionamentoTechException("Veículo já está estacionado no local");
        }

        Multa multa = multaMapper.toModel(multaDTO);
        multa.setIdVeiculo(idUsuario);
        multa.setId(UUID.randomUUID());

        multa = multaRepository.save(multa);

        return multaMapper.toDTO(multa);
    }
}
