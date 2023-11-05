package com.fiap.techchallenge.estacionamentotech.services.impl;

import com.fiap.techchallenge.estacionamentotech.dtos.VoucherEstacionamentoDTO;
import com.fiap.techchallenge.estacionamentotech.entities.VoucherEstacionamento;
import com.fiap.techchallenge.estacionamentotech.mappers.VoucherEstacionamentoMapper;
import com.fiap.techchallenge.estacionamentotech.repositories.VoucherEstacionamentoRepository;
import com.fiap.techchallenge.estacionamentotech.services.EmailService;
import com.fiap.techchallenge.estacionamentotech.services.PagamentoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public class PagamentoServiceImpl implements PagamentoService {

    private EmailService emailService;
    private VoucherEstacionamentoMapper voucherEstacionamentoMapper;
    private VoucherEstacionamentoRepository voucherEstacionamentoRepository;

    @Autowired
    public PagamentoServiceImpl(EmailService emailService, VoucherEstacionamentoMapper voucherEstacionamentoMapper) {
        this.emailService = emailService;
        this.voucherEstacionamentoMapper = voucherEstacionamentoMapper;
    }

    @Override
    public void registrarPagamento(Long idVeiculoEstacionado, VoucherEstacionamentoDTO voucherEstacionamentoDTO) {

        VoucherEstacionamento voucherEstacionamento = voucherEstacionamentoMapper.toEntity(voucherEstacionamentoDTO);
        voucherEstacionamento.setDataHoraRegistro(LocalDateTime.now());
        voucherEstacionamento.setIdVeiculoEstacionado(idVeiculoEstacionado);

        voucherEstacionamento = voucherEstacionamentoRepository.save(voucherEstacionamento);
    }
}
