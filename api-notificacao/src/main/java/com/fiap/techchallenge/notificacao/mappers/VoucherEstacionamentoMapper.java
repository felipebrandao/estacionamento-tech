package com.fiap.techchallenge.notificacao.mappers;

import com.fiap.techchallenge.notificacao.dtos.VoucherEstacionamentoDTO;
import com.fiap.techchallenge.notificacao.entities.VoucherEstacionamento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VoucherEstacionamentoMapper {

    VoucherEstacionamentoDTO toDTO(VoucherEstacionamento voucherEstacionamento);

    @Mapping(target = "veiculoEstacionado", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "idVeiculoEstacionado", ignore = true)
    @Mapping(target = "dataHoraRegistro", ignore = true)
    VoucherEstacionamento toEntity(VoucherEstacionamentoDTO voucherEstacionamentoDTO);

    List<VoucherEstacionamentoDTO> toDTOList(List<VoucherEstacionamento> voucherEstacionamentoList);
}
