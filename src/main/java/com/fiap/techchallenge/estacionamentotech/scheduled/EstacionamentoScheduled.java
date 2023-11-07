package com.fiap.techchallenge.estacionamentotech.scheduled;

import org.springframework.scheduling.annotation.Scheduled;

public class EstacionamentoScheduled {

    @Scheduled(cron = "0 */5 * * * *")
    public void enviarNotificacoesEstacionamentoEstaPertoDoFim() {
        // Implemente a lógica para buscar e enviar notificações de estacionamento perto do fim.
        // Você pode usar o banco de dados para consultar estacionamentos próximos do vencimento.
        // Envie notificações aos usuários conforme necessário.
    }

    @Scheduled(cron = "0 */5 * * * *")
    public void verificarEstacionamentoExpirado() {
        // Implemente a lógica para buscar e enviar notificações de estacionamentos expirados.
        // Você pode usar o banco de dados para consultar estacionamentos expirados.
        // Envie notificações aos usuários conforme necessário.
    }


}
