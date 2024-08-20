package com.fiap.techchallenge.veiculo.services;

import com.fiap.techchallenge.veiculo.dtos.VeiculoDTO;

import java.util.List;
import java.util.UUID;

public interface VeiculoService {
    VeiculoDTO cadastrarVeiculo(VeiculoDTO veiculoDTO, UUID idUsuario);

    List<VeiculoDTO> listarVeiculosPorUsuario(UUID idUsuario);

    VeiculoDTO buscarVeiculoPorIdEUsuario(Long id, UUID idUsuario);

    void excluirVeiculo(Long id, UUID idUsuario);
}
