package com.fiap.techchallenge.gateway.filter;

import com.fiap.techchallenge.gateway.feign.UsuarioFeignClient;
import com.fiap.techchallenge.gateway.feign.dto.UsuarioDTO;
import com.fiap.techchallenge.gateway.strategy.AutorizacaoStrategy;
import com.fiap.techchallenge.gateway.strategy.AutorizacaoUsuarioComum;
import com.fiap.techchallenge.gateway.strategy.AutorizacaoUsuarioFiscal;
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

    private final UsuarioFeignClient usuarioFeignClient;
    private final Map<String, AutorizacaoStrategy> strategyMap;

    public AuthenticationFilter(UsuarioFeignClient usuarioFeignClient) {
        this.usuarioFeignClient = usuarioFeignClient;
        this.strategyMap = new HashMap<>();
        this.strategyMap.put("COMUM", new AutorizacaoUsuarioComum());
        this.strategyMap.put("FISCAL", new AutorizacaoUsuarioFiscal());
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
            UsuarioDTO usuarioDTO = usuarioFeignClient.validateToken(headers.toSingleValueMap());
            String tipoUsuario = usuarioDTO.getTipoUsuario();

            AutorizacaoStrategy strategy = strategyMap.get(tipoUsuario);

            if (strategy == null) {
                exchange.getResponse().setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
                return exchange.getResponse().setComplete();
            }

            if (!strategy.autorizar(requestPath, requestMethod, tipoUsuario)) {
                exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
                return exchange.getResponse().setComplete();
            }

            exchange = exchange.mutate()
                    .request(builder -> builder.header("username", usuarioDTO.getUsername())
                            .header("id_user", usuarioDTO.getId())
                            .header("nome_usuario", usuarioDTO.getNome())
                            .header("email_usuario", usuarioDTO.getEmail())
                            .header("role", usuarioDTO.getTipoUsuario())).build();

            return chain.filter(exchange);
        } catch (Exception  e) {
            exchange.getResponse().setStatusCode(HttpStatus.BAD_GATEWAY);
            return exchange.getResponse().setComplete();
        }
    }

}
