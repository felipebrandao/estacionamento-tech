package com.fiap.techchallenge.multa.mappers;

import com.fiap.techchallenge.multa.dtos.MultaDTO;
import com.fiap.techchallenge.multa.entities.Multa;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MultaMapper {

    Multa toEntity(MultaDTO multaDTO);
    MultaDTO toDTO(Multa multa);
}
