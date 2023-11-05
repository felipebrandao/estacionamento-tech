package com.fiap.techchallenge.estacionamentotech.mappers;

import com.fiap.techchallenge.estacionamentotech.dtos.VeiculoDTO;
import com.fiap.techchallenge.estacionamentotech.entities.Veiculo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VeiculoMapper {

    @Mapping(target = "usuario", ignore = true)
    @Mapping(target = "idUsuario", ignore = true)
    Veiculo toEntity(VeiculoDTO veiculoDTO);
    VeiculoDTO toDTO(Veiculo veiculo);

    List<VeiculoDTO> toDTOList(List<Veiculo> veiculos);
}
