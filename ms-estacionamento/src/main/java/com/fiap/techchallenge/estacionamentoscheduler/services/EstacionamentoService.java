package com.fiap.techchallenge.estacionamentoscheduler.services;

import com.fiap.techchallenge.estacionamentoscheduler.dtos.LocalEstacionamentoDTO;
import com.fiap.techchallenge.estacionamentoscheduler.dtos.VeiculoEstacionadoDTO;
import com.fiap.techchallenge.estacionamentoscheduler.dtos.VoucherEstacionamentoDTO;

import java.util.List;
import java.util.UUID;

public interface EstacionamentoService {
    LocalEstacionamentoDTO cadastrarLocalEstacionamento(LocalEstacionamentoDTO localDTO);

    List<LocalEstacionamentoDTO> listarLocaisEstacionamento();

    VeiculoEstacionadoDTO registrarEstacionamento(VeiculoEstacionadoDTO veiculoEstacionadoDTO, UUID idUsuario);

    VoucherEstacionamentoDTO adicionarHorasDeEstacionamento(UUID idVeiculoEstacionado, VoucherEstacionamentoDTO voucherEstacionamentoDTO);

    void enviarNotificacoesEstacionamentoEstaPertoDoFim();

    void estacionamentoExpirado();

    VeiculoEstacionadoDTO veiculoComEstacionamentoAtivo(UUID idVeiculo);

    List<VeiculoEstacionadoDTO> buscarVeiculoEstacionadosPertoDoFim();

    List<VeiculoEstacionadoDTO> buscarVeiculosEstacionadosExpirados();
}
