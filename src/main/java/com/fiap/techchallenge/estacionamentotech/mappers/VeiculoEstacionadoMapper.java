package com.fiap.techchallenge.estacionamentotech.mappers;

import com.fiap.techchallenge.estacionamentotech.dtos.VeiculoEstacionadoDTO;
import com.fiap.techchallenge.estacionamentotech.entities.VeiculoEstacionado;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface VeiculoEstacionadoMapper {

    @Mapping(target = "veiculo", ignore = true)
    @Mapping(target = "usuario", ignore = true)
    @Mapping(target = "status", ignore = true)
    VeiculoEstacionado toEntity(VeiculoEstacionadoDTO veiculoEstacionadoDTO);

    @Mapping(target = "voucherEstacionamento", ignore = true)
    VeiculoEstacionadoDTO toDTO(VeiculoEstacionado veiculoEstacionado);
}
