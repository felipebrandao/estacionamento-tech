package com.fiap.techchallenge.multa.mappers;

import com.fiap.techchallenge.multa.dtos.MultaDTO;
import com.fiap.techchallenge.multa.model.Multa;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MultaMapper {
    Multa toModel(MultaDTO multaDTO);
    MultaDTO toDTO(Multa multa);
}
