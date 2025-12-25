package com.example.gateway_service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class FallbackController {

    @GetMapping("/fallback/produit")
    public Mono<String> fallbackProduitGet() {
        return Mono.just("Service Produit is currently unavailable, please try again later.");
    }

    @GetMapping("/fallback/commande")
    public Mono<String> fallbackCommandeGet() {
        return Mono.just("Service Commande is currently unavailable, please try again later.");
    }

    @PostMapping("/fallback/commande")
    public Mono<String> fallbackCommandePost() {
        return Mono.just("Service Commande is currently unavailable, please try again later.");
    }

    @GetMapping("/fallback/catalog")
    public Mono<String> fallbackCatalog() {
        return Mono.just("Service Catalog is currently unavailable, please try again later.");
    }
}
