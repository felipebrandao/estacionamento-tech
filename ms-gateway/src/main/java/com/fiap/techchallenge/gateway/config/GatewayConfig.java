package com.fiap.techchallenge.gateway.config;

import com.fiap.techchallenge.gateway.filter.AuthenticationFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Value("${ms-auth.url}")
    private String msAuthUrl;

    @Value("${ms-user.url}")
    private String msUserUrl;

    @Value("${ms-vehicle.url}")
    private String msVehicleUrl;

    @Value("${ms-parking.url}")
    private String msParkingUrl;

    @Value("${ms-parking-ticket.url}")
    private String msParkingTicketUrl;

    private final AuthenticationFilter filter;

    public GatewayConfig(AuthenticationFilter filter) {
        this.filter = filter;
    }

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/auth/**")
                        .uri(msAuthUrl))

                .route(r -> r.path("/user/**")
                        .uri(msUserUrl))

                .route(r -> r.path("/vehicle/**")
                        .filters(f -> f.filter(filter))
                        .uri(msVehicleUrl))

                .route(r -> r.path("/parking/**")
                        .filters(f -> f.filter(filter))
                        .uri(msParkingUrl))

                .route(r -> r.path("/parking-ticket/**")
                        .filters(f -> f.filter(filter))
                        .uri(msParkingTicketUrl))
                .build();
    }

}

