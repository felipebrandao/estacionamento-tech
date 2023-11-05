package com.fiap.techchallenge.estacionamentotech.mappers;

import com.fiap.techchallenge.estacionamentotech.dtos.VoucherEstacionamentoDTO;
import com.fiap.techchallenge.estacionamentotech.entities.VoucherEstacionamento;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VoucherEstacionamentoMapper {

    VoucherEstacionamento toEntity(VoucherEstacionamentoDTO voucherEstacionamentoDTO);
    VoucherEstacionamentoDTO toDTO(VoucherEstacionamento voucherEstacionamento);
}
