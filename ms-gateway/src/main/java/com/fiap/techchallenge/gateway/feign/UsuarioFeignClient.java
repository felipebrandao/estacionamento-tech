package com.fiap.techchallenge.gateway.feign;

import com.fiap.techchallenge.gateway.feign.dto.UsuarioDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Map;

@FeignClient(name = "api-usuario", url = "${api-usuario.url}")
public interface UsuarioFeignClient {

    @GetMapping("/api/usuario/validate-token")
    UsuarioDTO validateToken(@RequestHeader Map<String, String> headerMap);
}
