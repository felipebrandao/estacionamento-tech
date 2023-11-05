package com.fiap.techchallenge.estacionamentotech.services.impl;

import com.fiap.techchallenge.estacionamentotech.dtos.VeiculoDTO;
import com.fiap.techchallenge.estacionamentotech.entities.Usuario;
import com.fiap.techchallenge.estacionamentotech.entities.Veiculo;
import com.fiap.techchallenge.estacionamentotech.mappers.VeiculoMapper;
import com.fiap.techchallenge.estacionamentotech.repositories.VeiculoRepository;
import com.fiap.techchallenge.estacionamentotech.services.VeiculoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public VeiculoDTO cadastrarVeiculo(VeiculoDTO veiculoDTO, Usuario usuario) {

        Veiculo veiculo = veiculoMapper.toEntity(veiculoDTO);
        veiculo.setIdUsuario(usuario.getIdUsuario());

        veiculo = veiculoRepository.save(veiculo);
        return veiculoMapper.toDTO(veiculo);
    }

    @Override
    public List<VeiculoDTO> listarVeiculosPorUsuario(Usuario usuario) {
        List<Veiculo> veiculos = veiculoRepository.findByIdUsuario(usuario.getIdUsuario());
        return veiculoMapper.toDTOList(veiculos);
    }

    @Override
    public VeiculoDTO buscarVeiculoPorIdEUsuario(Long id, Usuario usuario) {
        Veiculo veiculo = veiculoRepository.findByIdAndIdUsuario(id, usuario.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Veiculo com ID " + id + " não encontrada."));
        return veiculoMapper.toDTO(veiculo);
    }

    @Override
    public VeiculoDTO atualizarVeiculo(Long id, VeiculoDTO veiculo, Usuario usuario) {
        return null;
    }

    @Override
    public void excluirVeiculo(Long id, Usuario usuario) {
        Veiculo veiculo = veiculoRepository.findByIdAndIdUsuario(id, usuario.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Veiculo com ID " + id + " não encontrado"));

        veiculoRepository.delete(veiculo);
    }
}
