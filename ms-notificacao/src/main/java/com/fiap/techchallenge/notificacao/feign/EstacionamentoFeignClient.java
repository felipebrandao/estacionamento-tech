package com.fiap.techchallenge.notificacao.feign;

import com.fiap.techchallenge.multa.feign.dto.VeiculoEstacionadoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "ms-estacionamento", url = "${ms-estacionamento.url}")
public interface EstacionamentoFeignClient {

    @GetMapping("/veiculo-com-estacionamento/{idVeiculo}")
    VeiculoEstacionadoDTO veiculoComEstacionamentoAtivo(@PathVariable UUID idVeiculo);
}
