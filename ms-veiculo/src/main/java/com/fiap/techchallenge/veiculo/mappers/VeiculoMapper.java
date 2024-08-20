package com.fiap.techchallenge.veiculo.mappers;

import com.fiap.techchallenge.veiculo.dtos.VeiculoDTO;
import com.fiap.techchallenge.veiculo.model.Veiculo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VeiculoMapper {

    @Mapping(target = "idUsuario", ignore = true)
    Veiculo toModel(VeiculoDTO veiculoDTO);
    VeiculoDTO toDTO(Veiculo veiculo);

    List<VeiculoDTO> toDTOList(List<Veiculo> veiculos);
}
