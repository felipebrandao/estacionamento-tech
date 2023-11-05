package com.fiap.techchallenge.estacionamentotech.mappers;

import com.fiap.techchallenge.estacionamentotech.dtos.VeiculoEstacionadoDTO;
import com.fiap.techchallenge.estacionamentotech.entities.VeiculoEstacionado;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VeiculoEstacionadoMapper {

    VeiculoEstacionado toEntity(VeiculoEstacionadoDTO veiculoEstacionadoDTO);
    VeiculoEstacionadoDTO toDTO(VeiculoEstacionado veiculoEstacionado);
}
