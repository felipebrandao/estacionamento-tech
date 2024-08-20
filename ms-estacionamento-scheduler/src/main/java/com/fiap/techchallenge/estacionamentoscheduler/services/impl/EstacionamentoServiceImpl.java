package com.fiap.techchallenge.estacionamentoscheduler.services.impl;

import com.fiap.techchallenge.estacionamentoscheduler.feign.EstacionamentoFeignClient;
import com.fiap.techchallenge.estacionamentoscheduler.feign.dto.VeiculoEstacionadoDTO;
import com.fiap.techchallenge.estacionamentoscheduler.producer.NotificacaoProducer;
import com.fiap.techchallenge.estacionamentoscheduler.producer.dto.NotificacaoDTO;
import com.fiap.techchallenge.estacionamentoscheduler.producer.enums.TipoEmailEnum;
import com.fiap.techchallenge.estacionamentoscheduler.services.EstacionamentoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class EstacionamentoServiceImpl implements EstacionamentoService {

    private final NotificacaoProducer notificacaoProducer;
    private final EstacionamentoFeignClient estacionamentoFeignClient;

    @Autowired
    public EstacionamentoServiceImpl(NotificacaoProducer notificacaoProducer, EstacionamentoFeignClient estacionamentoFeignClient) {
        this.notificacaoProducer = notificacaoProducer;
        this.estacionamentoFeignClient = estacionamentoFeignClient;
    }

    private void envioDeEmailEstacionamento(UUID veiculoEstacionado, TipoEmailEnum tipoEmail) {

        NotificacaoDTO notificacaoDTO = NotificacaoDTO.builder()
                .idNotificacao(UUID.randomUUID())
                .idVeiculo(veiculoEstacionado)
                .tipoEmail(tipoEmail)
                .build();

        notificacaoProducer.sendNotificacao(notificacaoDTO);
    }

    @Override
    public void enviarNotificacoesEstacionamentoEstaPertoDoFim() {
        
        List<VeiculoEstacionadoDTO> estacionamentosPertoDoFim = estacionamentoFeignClient.buscarVeiculoEstacionadosPertoDoFim();

        for (VeiculoEstacionadoDTO estacionado : estacionamentosPertoDoFim) {
            envioDeEmailEstacionamento(estacionado.getIdVeiculo(), TipoEmailEnum.EXPIRACAO);
        }
    }

    @Override
    public void estacionamentoExpirado() {

        List<VeiculoEstacionadoDTO> estacionamentosExpirado = estacionamentoFeignClient.buscarVeiculosEstacionadosExpirados();

        for (VeiculoEstacionadoDTO estacionado : estacionamentosExpirado) {
            envioDeEmailEstacionamento(estacionado.getIdVeiculo(), TipoEmailEnum.EXPIRADO);
        }
    }
}
