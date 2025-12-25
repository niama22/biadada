package com.example.service_commande.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .filter((request, next) -> {
                    var authentication = org.springframework.security.core.context.SecurityContextHolder.getContext()
                            .getAuthentication();
                    if (authentication instanceof org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken jwtToken) {
                        var token = jwtToken.getToken().getTokenValue();
                        var newRequest = org.springframework.web.reactive.function.client.ClientRequest.from(request)
                                .header("Authorization", "Bearer " + token)
                                .build();
                        return next.exchange(newRequest);
                    }
                    return next.exchange(request);
                })
                .build();
    }
}