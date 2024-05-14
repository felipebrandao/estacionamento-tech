package com.fiap.techchallenge.estacionamento.mappers;

import com.fiap.techchallenge.estacionamento.dtos.LocalEstacionamentoDTO;
import com.fiap.techchallenge.estacionamento.entities.LocalEstacionamento;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LocalEstacionamentoMapper {
    LocalEstacionamento toEntity(LocalEstacionamentoDTO localDTO);

    LocalEstacionamentoDTO toDTO(LocalEstacionamento localEstacionamento);

    List<LocalEstacionamentoDTO> toDTOList(List<LocalEstacionamento> localEstacionamentoList);
}
