package com.fiap.techchallenge.estacionamentotech.scheduled;

import com.fiap.techchallenge.estacionamentotech.services.EstacionamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class EstacionamentoScheduled {

    private final EstacionamentoService estacionamentoService;

    @Autowired
    public EstacionamentoScheduled(EstacionamentoService estacionamentoService) {
        this.estacionamentoService = estacionamentoService;
    }

    @Scheduled(cron = "0 */5 * * * *")
    public void enviarNotificacoesEstacionamentoEstaPertoDoFim() {
        estacionamentoService.enviarNotificacoesEstacionamentoEstaPertoDoFim();
    }

    @Scheduled(cron = "0 */10 * * * *")
    public void verificarEstacionamentoExpirado() {
        estacionamentoService.estacionamentoExpirado();
    }

}
