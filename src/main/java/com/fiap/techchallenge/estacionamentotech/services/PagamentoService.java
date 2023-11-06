package com.fiap.techchallenge.estacionamentotech.services;

import com.fiap.techchallenge.estacionamentotech.dtos.VoucherEstacionamentoDTO;
import org.springframework.stereotype.Service;

@Service
public interface PagamentoService {
    public VoucherEstacionamentoDTO registrarPagamento(Long idVeiculoEstacionado, VoucherEstacionamentoDTO voucherEstacionamentoDTO);
}
