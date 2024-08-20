package com.fiap.techchallenge.estacionamentoscheduler.mappers;

import com.fiap.techchallenge.estacionamentoscheduler.dtos.VeiculoEstacionadoDTO;
import com.fiap.techchallenge.estacionamentoscheduler.model.VeiculoEstacionado;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VeiculoEstacionadoMapper {
    VeiculoEstacionadoDTO toDTO(VeiculoEstacionado veiculoEstacionado);

    List<VeiculoEstacionadoDTO> toDTOList(List<VeiculoEstacionado> estacionamentosPertoDoFim);
}
