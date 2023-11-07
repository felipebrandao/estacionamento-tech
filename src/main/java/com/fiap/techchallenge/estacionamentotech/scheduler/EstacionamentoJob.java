package com.fiap.techchallenge.estacionamentotech.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EstacionamentoJob {


    @Scheduled(fixedDelayString = "${job.execution.time}")
    public void verificarEstacionamentosPertoDoFim() {
        System.out.println("Executando job..");
    }
/*
    public void execute(JobExecutionContext context) throws JobExecutionException {
        // Lógica de verificação do estacionamento
        // Consulte o banco de dados para obter informações sobre o estacionamento
        // Compare o horário atual com o horário de término do estacionamento

        // Se o horário atual estiver a X minutos (ou qualquer período definido) do término, envie uma notificação
        if (estacionamentoEstaPertoDoFim()) {
            enviarNotificacaoDeEstacionamento();
        }
    }

    private boolean estacionamentoEstaPertoDoFim() {
        // Implemente a lógica para verificar se o estacionamento está perto do término
        return false;
    }

    private void enviarNotificacaoDeEstacionamento() {
        // Implemente a lógica para enviar uma notificação ao usuário
        // Isso pode ser feito por e-mail, SMS, notificação push, etc.
    }

 */
}
