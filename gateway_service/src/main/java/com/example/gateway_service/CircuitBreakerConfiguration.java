package com.example.gateway_service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class CircuitBreakerConfiguration {

    @Bean
    public RouterFunction<ServerResponse> fallbackServiceRoute() {
        return route(GET("/fallback/produit"),
                request -> ServerResponse.ok()
                        .bodyValue("Service Produit is currently unavailable, please try again later."))
                .andRoute(GET("/fallback/commande"),
                        request -> ServerResponse.ok()
                                .bodyValue("Service Commande is currently unavailable, please try again later."))
                .andRoute(GET("/fallback/catalog"),
                        request -> ServerResponse.ok()
                                .bodyValue("Service Catalog is currently unavailable, please try again later."));
    }
}
