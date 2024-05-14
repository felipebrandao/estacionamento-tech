package com.fiap.techchallenge.estacionamento.mappers;

import com.fiap.techchallenge.estacionamento.dtos.VoucherEstacionamentoDTO;
import com.fiap.techchallenge.estacionamento.entities.VoucherEstacionamento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VoucherEstacionamentoMapper {

    VoucherEstacionamentoDTO toDTO(VoucherEstacionamento voucherEstacionamento);

    VoucherEstacionamento toEntity(VoucherEstacionamentoDTO voucherEstacionamentoDTO);

    List<VoucherEstacionamentoDTO> toDTOList(List<VoucherEstacionamento> voucherEstacionamentoList);
}
