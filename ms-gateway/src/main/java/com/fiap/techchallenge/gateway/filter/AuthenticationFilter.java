package com.fiap.techchallenge.gateway.filter;

import com.fiap.techchallenge.gateway.feign.AuthFeignClient;
import com.fiap.techchallenge.gateway.feign.dto.UserDTO;
import com.fiap.techchallenge.gateway.strategy.AuthorizationStrategy;
import com.fiap.techchallenge.gateway.strategy.CommonUserAuthorization;
import com.fiap.techchallenge.gateway.strategy.InspectorUserAuthorization;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@RefreshScope
@Component
public class AuthenticationFilter implements GatewayFilter {

    private final AuthFeignClient authFeignClient;
    private final Map<String, AuthorizationStrategy> strategyMap;

    public AuthenticationFilter(AuthFeignClient authFeignClient) {
        this.authFeignClient = authFeignClient;
        this.strategyMap = new HashMap<>();
        this.strategyMap.put("COMUM", new CommonUserAuthorization());
        this.strategyMap.put("FISCAL", new InspectorUserAuthorization());
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        HttpHeaders headers = exchange.getRequest().getHeaders();
        String authorizationHeader = headers.getFirst(HttpHeaders.AUTHORIZATION);

        String requestPath = exchange.getRequest().getPath().toString();
        HttpMethod requestMethod = exchange.getRequest().getMethod();

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        try {
            UserDTO userDTO = authFeignClient.validateToken(headers.toSingleValueMap());
            String userType = userDTO.getUserType();

            AuthorizationStrategy strategy = strategyMap.get(userType.toUpperCase());

            if (strategy == null) {
                exchange.getResponse().setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
                return exchange.getResponse().setComplete();
            }

            if (!strategy.authorize(requestPath, requestMethod, userType)) {
                exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
                return exchange.getResponse().setComplete();
            }

            exchange = exchange.mutate()
                    .request(builder -> builder.header(HttpHeaders.AUTHORIZATION, authorizationHeader))
                    .build();

            return chain.filter(exchange);
        } catch (Exception  e) {
            exchange.getResponse().setStatusCode(HttpStatus.BAD_REQUEST);
            return exchange.getResponse().setComplete();
        }
    }

}
