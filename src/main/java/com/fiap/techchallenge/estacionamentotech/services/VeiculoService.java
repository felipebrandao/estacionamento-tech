package com.fiap.techchallenge.estacionamentotech.services;

import com.fiap.techchallenge.estacionamentotech.dtos.VeiculoDTO;
import com.fiap.techchallenge.estacionamentotech.entities.Usuario;

import java.util.List;

public interface VeiculoService {
    VeiculoDTO cadastrarVeiculo(VeiculoDTO veiculoDTO, Usuario usuario);

    List<VeiculoDTO> listarVeiculosPorUsuario(Usuario usuario);

    VeiculoDTO buscarVeiculoPorIdEUsuario(Long id, Usuario usuario);

    VeiculoDTO atualizarVeiculo(Long id, VeiculoDTO veiculo, Usuario usuario);

    void excluirVeiculo(Long id, Usuario usuario);
}
