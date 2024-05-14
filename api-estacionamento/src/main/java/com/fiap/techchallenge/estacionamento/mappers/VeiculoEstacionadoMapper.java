package com.fiap.techchallenge.estacionamento.mappers;

import com.fiap.techchallenge.estacionamento.dtos.VeiculoEstacionadoDTO;
import com.fiap.techchallenge.estacionamento.entities.VeiculoEstacionado;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface VeiculoEstacionadoMapper {

    @Mapping(target = "status", ignore = true)
    VeiculoEstacionado toEntity(VeiculoEstacionadoDTO veiculoEstacionadoDTO);

    @Mapping(target = "voucherEstacionamento", ignore = true)
    VeiculoEstacionadoDTO toDTO(VeiculoEstacionado veiculoEstacionado);
}
