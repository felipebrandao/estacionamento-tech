package com.fiap.techchallenge.estacionamentoscheduler.feign;

import com.fiap.techchallenge.estacionamentoscheduler.feign.dto.VeiculoEstacionadoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "ms-estacionamento", url = "${ms-estacionamento.url}")
public interface EstacionamentoFeignClient {

    @GetMapping("/veiculos-estacionados/perto-do-fim")
    List<VeiculoEstacionadoDTO> buscarVeiculoEstacionadosPertoDoFim();

    @GetMapping("/veiculos-estacionados/expirados")
    List<VeiculoEstacionadoDTO> buscarVeiculosEstacionadosExpirados();
}
