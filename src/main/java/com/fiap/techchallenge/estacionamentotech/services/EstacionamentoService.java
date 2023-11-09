package com.fiap.techchallenge.estacionamentotech.services;

import com.fiap.techchallenge.estacionamentotech.dtos.LocalEstacionamentoDTO;
import com.fiap.techchallenge.estacionamentotech.dtos.VeiculoEstacionadoDTO;
import com.fiap.techchallenge.estacionamentotech.dtos.VoucherEstacionamentoDTO;
import com.fiap.techchallenge.estacionamentotech.entities.Usuario;

import java.util.List;

public interface EstacionamentoService {
    LocalEstacionamentoDTO cadastrarLocalEstacionamento(LocalEstacionamentoDTO localDTO);

    List<LocalEstacionamentoDTO> listarLocaisEstacionamento();

    VeiculoEstacionadoDTO registrarEstacionamento(VeiculoEstacionadoDTO veiculoEstacionadoDTO, Usuario usuario);

    VoucherEstacionamentoDTO adicionarHorasDeEstacionamento(Long idVeiculoEstacionado, VoucherEstacionamentoDTO voucherEstacionamentoDTO);

    void enviarNotificacoesEstacionamentoEstaPertoDoFim();

    void estacionamentoExpirado();
}
