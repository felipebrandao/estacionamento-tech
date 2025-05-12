package com.fiap.techchallenge.gateway.feign;

import com.fiap.techchallenge.gateway.feign.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Map;

@FeignClient(name = "ms-auth", url = "${ms-auth.url}")
public interface AuthFeignClient {

    @GetMapping("/validate-token")
    UserDTO validateToken(@RequestHeader Map<String, String> headerMap);

}
