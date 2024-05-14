package com.fiap.techchallenge.estacionamento.producer;

import com.fiap.techchallenge.estacionamento.producer.dto.NotificacaoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class NotificacaoProducer {

    private final KafkaTemplate<String, NotificacaoDTO> kafkaTemplate;

    @Autowired
    public NotificacaoProducer(KafkaTemplate<String, NotificacaoDTO> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendNotificacao(NotificacaoDTO notificacaoDTO) {
        kafkaTemplate.send("fila-notificacoes", notificacaoDTO);
    }
}
