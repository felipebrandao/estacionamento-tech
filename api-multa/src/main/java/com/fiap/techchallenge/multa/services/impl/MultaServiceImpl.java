package com.fiap.techchallenge.multa.services.impl;

import com.fiap.techchallenge.multa.dtos.MultaDTO;
import com.fiap.techchallenge.multa.entities.Multa;
import com.fiap.techchallenge.multa.entities.Usuario;
import com.fiap.techchallenge.multa.entities.VeiculoEstacionado;
import com.fiap.techchallenge.multa.exceptions.VeiculoException;
import com.fiap.techchallenge.multa.mappers.MultaMapper;
import com.fiap.techchallenge.multa.repositories.MultaRepository;
import com.fiap.techchallenge.multa.repositories.VeiculoEstacionadoRepository;
import com.fiap.techchallenge.multa.services.MultaService;
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

        VeiculoEstacionado buscaVeiculoEstacionado = veiculoEstacionadoRepository.
                findByIdVeiculoAndIdLocalEstacionamentoAndStatusTrue(multaDTO.getIdVeiculo(),
                                                                     multaDTO.getIdLocalEstacionamento());

        if (buscaVeiculoEstacionado != null) {
            throw new VeiculoException("O veículo está com estacionamento registrado");
        }

        Multa multa = multaMapper.toEntity(multaDTO);
        multa.setIdVeiculo(usuario.getIdUsuario());

        multa = multaRepository.save(multa);

        return multaMapper.toDTO(multa);
    }
}
