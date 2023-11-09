package com.fiap.techchallenge.estacionamentotech.mappers;

import com.fiap.techchallenge.estacionamentotech.dtos.MultaDTO;
import com.fiap.techchallenge.estacionamentotech.entities.Multa;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MultaMapper {

    Multa toEntity(MultaDTO multaDTO);
    MultaDTO toDTO(Multa multa);
}
