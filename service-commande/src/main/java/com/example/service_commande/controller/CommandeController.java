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
    public String etablirCommande(@RequestBody CommandeRequest commandeRequest) {
        commandeService.etablirCommande(commandeRequest);
        return "la commande etablie avec succes";
    }

}
