package com.fiap.techchallenge.estacionamentotech.services.impl;

import com.fiap.techchallenge.estacionamentotech.dtos.MultaDTO;
import com.fiap.techchallenge.estacionamentotech.entities.Multa;
import com.fiap.techchallenge.estacionamentotech.entities.Usuario;
import com.fiap.techchallenge.estacionamentotech.entities.VeiculoEstacionado;
import com.fiap.techchallenge.estacionamentotech.exceptions.VeiculoException;
import com.fiap.techchallenge.estacionamentotech.mappers.MultaMapper;
import com.fiap.techchallenge.estacionamentotech.repositories.MultaRepository;
import com.fiap.techchallenge.estacionamentotech.repositories.VeiculoEstacionadoRepository;
import com.fiap.techchallenge.estacionamentotech.services.MultaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class MultaServiceImpl implements MultaService {

    private final VeiculoEstacionadoRepository veiculoEstacionadoRepository;
    private final MultaRepository multaRepository;

    private final MultaMapper multaMapper;

    @Autowired
    public MultaServiceImpl(VeiculoEstacionadoRepository veiculoEstacionadoRepository, MultaRepository multaRepository, MultaMapper multaMapper) {
        this.veiculoEstacionadoRepository = veiculoEstacionadoRepository;
        this.multaRepository = multaRepository;
        this.multaMapper = multaMapper;
    }

    @Override
    public MultaDTO registrarMulta(MultaDTO multaDTO, Usuario usuario) {

        Optional<VeiculoEstacionado> buscaVeiculoEstacionado = veiculoEstacionadoRepository.
                findByIdVeiculoAndIdLocalEstacionamentoAndStatusTrue(multaDTO.getIdVeiculo(),
                                                                     multaDTO.getIdLocalEstacionamento());

        if (buscaVeiculoEstacionado.isEmpty()) {
            throw new VeiculoException("O veículo não está na rua.");
        }

        Multa multa = multaMapper.toEntity(multaDTO);
        multa.setIdVeiculo(usuario.getIdUsuario());

        multa = multaRepository.save(multa);

        return multaMapper.toDTO(multa);
    }

}
