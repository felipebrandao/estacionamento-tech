package com.fiap.techchallenge.gateway.config;

import com.fiap.techchallenge.gateway.filter.AuthenticationFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Value("${ms-usuario.url}")
    private String msUsuarioUrl;

    @Value("${ms-veiculo.url}")
    private String msVeiculoUrl;

    @Value("${ms-estacionamento.url}")
    private String msEstacionamentoUrl;

    @Value("${ms-multa.url}")
    private String msMultaUrl;

    private final AuthenticationFilter filter;

    public GatewayConfig(AuthenticationFilter filter) {
        this.filter = filter;
    }

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/usuario/**")
                        .uri(msUsuarioUrl))

                .route(r -> r.path("/veiculo/**")
                        .filters(f -> f.filter(filter))
                        .uri(msVeiculoUrl))

                .route(r -> r.path("/estacionamento/**")
                        .filters(f -> f.filter(filter))
                        .uri(msEstacionamentoUrl))

                .route(r -> r.path("/multa/**")
                        .filters(f -> f.filter(filter))
                        .uri(msMultaUrl))
                .build();


    }

}

