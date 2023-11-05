package com.fiap.techchallenge.estacionamentotech.services;

import com.fiap.techchallenge.estacionamentotech.dtos.MultaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MultaService {
/*
    private final VeiculoRepository veiculoRepository;
    private final MultaRepository multaRepository;

    @Autowired
    public MultaService(VeiculoRepository veiculoRepository, MultaRepository multaRepository) {
        this.veiculoRepository = veiculoRepository;
        this.multaRepository = multaRepository;
    }

    public Multa registrarMulta(MultaDTO multaDto) {
        // Verifique se o veículo está na rua
        Veiculo veiculo = veiculoRepository.findByNome(multaDto.getVeiculo());

        if (veiculo == null || !veiculo.isNaRua()) {
            throw new IllegalArgumentException("O veículo não está na rua.");
        }

        // Verifique se o ID corresponde ao do veículo
        if (!veiculo.getId().equals(multaDto.getId())) {
            throw new IllegalArgumentException("O ID da multa não corresponde ao do veículo.");
        }

        // Crie uma instância de Multa e persista-a no banco de dados
        Multa multa = new Multa();
        // Configure os detalhes da multa com base no seu requisito
        multa = multaRepository.save(multa);

        return multa;
    }
    */
}