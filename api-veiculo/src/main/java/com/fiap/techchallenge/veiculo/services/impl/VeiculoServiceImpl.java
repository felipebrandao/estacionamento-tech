package com.fiap.techchallenge.veiculo.services.impl;

import com.fiap.techchallenge.veiculo.dtos.VeiculoDTO;
import com.fiap.techchallenge.veiculo.entities.Veiculo;
import com.fiap.techchallenge.veiculo.mappers.VeiculoMapper;
import com.fiap.techchallenge.veiculo.repositories.VeiculoRepository;
import com.fiap.techchallenge.veiculo.services.VeiculoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class VeiculoServiceImpl implements VeiculoService {

    private final VeiculoRepository veiculoRepository;

    private final VeiculoMapper veiculoMapper;

    @Autowired
    public VeiculoServiceImpl(VeiculoRepository veiculoRepository, VeiculoMapper veiculoMapper) {
        this.veiculoRepository = veiculoRepository;
        this.veiculoMapper = veiculoMapper;
    }

    @Override
    public VeiculoDTO cadastrarVeiculo(VeiculoDTO veiculoDTO, UUID idUsuario) {

        Veiculo veiculo = veiculoMapper.toEntity(veiculoDTO);
        veiculo.setId(UUID.randomUUID());
        veiculo.setIdUsuario(idUsuario);

        veiculo = veiculoRepository.save(veiculo);
        return veiculoMapper.toDTO(veiculo);
    }

    @Override
    public List<VeiculoDTO> listarVeiculosPorUsuario(UUID idUsuario) {
        List<Veiculo> veiculos = veiculoRepository.findByIdUsuario(idUsuario);
        return veiculoMapper.toDTOList(veiculos);
    }

    @Override
    public VeiculoDTO buscarVeiculoPorIdEUsuario(Long id, UUID idUsuario) {
        Veiculo veiculo = veiculoRepository.findByIdAndIdUsuario(id, idUsuario)
                .orElseThrow(() -> new RuntimeException("Veiculo com ID " + id + " não encontrada."));
        return veiculoMapper.toDTO(veiculo);
    }
    @Override
    public void excluirVeiculo(Long id, UUID idUsuario) {
        Veiculo veiculo = veiculoRepository.findByIdAndIdUsuario(id, idUsuario)
                .orElseThrow(() -> new RuntimeException("Veiculo com ID " + id + " não encontrado"));

        veiculoRepository.delete(veiculo);
    }
}
