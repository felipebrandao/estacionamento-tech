package com.fiap.techchallenge.estacionamentotech.mappers;

import com.fiap.techchallenge.estacionamentotech.dtos.VeiculoDTO;
import com.fiap.techchallenge.estacionamentotech.entities.Veiculo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VeiculoMapper {

    Veiculo toEntity(VeiculoDTO veiculoDTO);
    VeiculoDTO toDTO(Veiculo veiculo);

    List<VeiculoDTO> toDTOList(List<Veiculo> veiculos);
}
