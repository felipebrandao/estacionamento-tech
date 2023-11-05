package com.fiap.techchallenge.estacionamentotech.services;

import com.fiap.techchallenge.estacionamentotech.dtos.LocalEstacionamentoDTO;
import com.fiap.techchallenge.estacionamentotech.dtos.VeiculoEstacionadoDTO;
import com.fiap.techchallenge.estacionamentotech.dtos.VoucherEstacionamentoDTO;

import java.util.List;

public interface EstacionamentoService {
    LocalEstacionamentoDTO cadastrarLocalEstacionamento(LocalEstacionamentoDTO localDTO);

    List<LocalEstacionamentoDTO> listarLocaisEstacionamento();

    VeiculoEstacionadoDTO registrarEstacionamento(VeiculoEstacionadoDTO veiculoEstacionadoDTO);

    VeiculoEstacionadoDTO adicionarHorasDeEstacionamento(Long idVeiculoEstacionado, VoucherEstacionamentoDTO voucherEstacionamentoDTO);
}
