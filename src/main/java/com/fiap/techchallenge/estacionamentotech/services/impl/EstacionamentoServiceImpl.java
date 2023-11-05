package com.fiap.techchallenge.estacionamentotech.services.impl;

import com.fiap.techchallenge.estacionamentotech.dtos.LocalEstacionamentoDTO;
import com.fiap.techchallenge.estacionamentotech.dtos.VeiculoEstacionadoDTO;
import com.fiap.techchallenge.estacionamentotech.dtos.VoucherEstacionamentoDTO;
import com.fiap.techchallenge.estacionamentotech.entities.LocalEstacionamento;
import com.fiap.techchallenge.estacionamentotech.mappers.LocalEstacionamentoMapper;
import com.fiap.techchallenge.estacionamentotech.repositories.LocalEstacionamentoRepository;
import com.fiap.techchallenge.estacionamentotech.services.EstacionamentoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class EstacionamentoServiceImpl implements EstacionamentoService {

    private LocalEstacionamentoRepository localEstacionamentoRepository;
    private LocalEstacionamentoMapper localEstacionamentoMapper;

    @Autowired
    public EstacionamentoServiceImpl(LocalEstacionamentoRepository localEstacionamentoRepository, LocalEstacionamentoMapper localEstacionamentoMapper) {
        this.localEstacionamentoRepository = localEstacionamentoRepository;
        this.localEstacionamentoMapper = localEstacionamentoMapper;
    }

    @Override
    public LocalEstacionamentoDTO cadastrarLocalEstacionamento(LocalEstacionamentoDTO localDTO) {
        LocalEstacionamento localEstacionamento = localEstacionamentoMapper.toEntity(localDTO);

        localEstacionamento = localEstacionamentoRepository.save(localEstacionamento);
        return localEstacionamentoMapper.toDTO(localEstacionamento);
    }

    @Override
    public List<LocalEstacionamentoDTO> listarLocaisEstacionamento() {
        List<LocalEstacionamento> localEstacionamentoList = localEstacionamentoRepository.findAll();
        return localEstacionamentoMapper.toDTOList(localEstacionamentoList);
    }

    //TODO tem que ser uma transação para registrar o pagamento e também o estacionamento
    @Override
    public VeiculoEstacionadoDTO registrarEstacionamento(VeiculoEstacionadoDTO veiculoEstacionadoDTO) {

        //TODO verificar se veículo já está estacionado
        //TODO ao concluir enviar email com pagamento e veiculo estacionado
        pagamentoService.registrarPagamento(veiculoEstacionadoDTO.getVeiculoId(), veiculoEstacionadoDTO.getVoucherEstacionamento().get(0));

        LocalEstacionamentoRepository.save();

        return null;
    }

    @Override
    public VeiculoEstacionadoDTO adicionarHorasDeEstacionamento(Long idVeiculoEstacionado, VoucherEstacionamentoDTO voucherEstacionamentoDTO) {
        //TODO verificar se veículo já está estacionado, ou expirou o prazo, criar um novo estacionamento
        //T0D0 registrar pagamento do estacionamento
        //TODO adicionar voucher ao estacionamento
        return null;
    }
}
