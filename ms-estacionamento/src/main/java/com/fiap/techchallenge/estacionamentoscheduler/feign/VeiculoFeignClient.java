package com.fiap.techchallenge.estacionamentoscheduler.feign;

import com.fiap.techchallenge.estacionamentoscheduler.feign.dto.VeiculoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.UUID;

@FeignClient(name = "api-veiculo", url = "${api-veiculo.url}")
public interface VeiculoFeignClient {

    @GetMapping("/api/veiculo/{id}")
    VeiculoDTO buscarVeiculoPorId(@RequestHeader("id_user") UUID idUsuario, @PathVariable UUID id);
}
