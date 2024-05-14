package com.fiap.techchallenge.gateway.config;

import com.fiap.techchallenge.gateway.filter.AuthenticationFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Value("${api-usuario.url}")
    private String apiUsuarioUrl;

    @Value("${api-veiculo.url}")
    private String apiVeiculoUrl;

    private final AuthenticationFilter filter;

    public GatewayConfig(AuthenticationFilter filter) {
        this.filter = filter;
    }

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/api/veiculo/**")
                        .filters(f -> f.filter(filter))
                        .uri(apiVeiculoUrl))

                .route(r -> r.path("/api/usuario/**")
                        .uri(apiUsuarioUrl))
                .build();
    }

}

