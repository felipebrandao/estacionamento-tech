package com.fiap.techchallenge.estacionamento.services;

import com.fiap.techchallenge.estacionamento.dtos.LocalEstacionamentoDTO;
import com.fiap.techchallenge.estacionamento.dtos.VeiculoEstacionadoDTO;
import com.fiap.techchallenge.estacionamento.dtos.VoucherEstacionamentoDTO;

import java.util.List;
import java.util.UUID;

public interface EstacionamentoService {
    LocalEstacionamentoDTO cadastrarLocalEstacionamento(LocalEstacionamentoDTO localDTO);

    List<LocalEstacionamentoDTO> listarLocaisEstacionamento();

    VeiculoEstacionadoDTO registrarEstacionamento(VeiculoEstacionadoDTO veiculoEstacionadoDTO, UUID idUsuario);

    VoucherEstacionamentoDTO adicionarHorasDeEstacionamento(UUID idVeiculoEstacionado, VoucherEstacionamentoDTO voucherEstacionamentoDTO);

    void enviarNotificacoesEstacionamentoEstaPertoDoFim();

    void estacionamentoExpirado();
}
