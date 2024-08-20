package com.fiap.techchallenge.notificacao.consumer;

import com.fiap.techchallenge.notificacao.dtos.NotificacaoDTO;
import com.fiap.techchallenge.notificacao.services.NotificacaoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class NotificacaoConsumer {

    private final NotificacaoService notificacaoService;

    @Autowired
    public NotificacaoConsumer(NotificacaoService notificacaoService) {
        this.notificacaoService = notificacaoService;
    }

    @KafkaListener(topics = "${spring.kafka.topic.estacionamento.envio-de-mail}")
    public void receiveEmailNotification(ConsumerRecord<String, NotificacaoDTO> record, Acknowledgment acknowledgment) {
        try {
            NotificacaoDTO notificacaoDTO = record.value();
            notificacaoService.enviarEmail(notificacaoDTO);
            acknowledgment.acknowledge();
        } catch (Exception e) {
            log.error("Erro ao enviar email: " + e.getMessage());
        }
    }
}
