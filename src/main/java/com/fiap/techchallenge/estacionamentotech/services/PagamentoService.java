package com.fiap.techchallenge.estacionamentotech.services;

import com.fiap.techchallenge.estacionamentotech.dtos.VoucherEstacionamentoDTO;

public interface PagamentoService {
    VoucherEstacionamentoDTO registrarPagamento(Long idVeiculoEstacionado, VoucherEstacionamentoDTO voucherEstacionamentoDTO);
}
