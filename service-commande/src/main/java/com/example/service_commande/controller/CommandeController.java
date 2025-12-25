package com.example.service_commande.controller;

import com.example.service_commande.dto.CommandeRequest;
import com.example.service_commande.service.CommandeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/commande")

public class CommandeController {
    @Autowired
    private  CommandeService commandeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker(name = "catalogue", fallbackMethod = "fallbackMethod")
    public String etablirCommande(@RequestBody CommandeRequest commandeRequest) {
        commandeService.etablirCommande(commandeRequest);
        return "la commande etablie avec succes";
    }

    public String fallbackMethod(CommandeRequest commandeRequest, RuntimeException ex) {
        System.err.println("Fallback triggered due to: " + ex.getMessage());
        ex.printStackTrace();
        return "Problème de connexion, Veuillez réssayer ";
    }
}
