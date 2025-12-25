package com.example.service_produit.controller;

import com.example.service_produit.DTO.*;
import com.example.service_produit.service.ProduitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produits")
@RequiredArgsConstructor
public class ProduitController {
    private final ProduitService produitService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduit(@RequestBody ProduitRequest produitRequest) {
        produitService.createProduit(produitRequest);
    }

    @GetMapping
    public List<ProduitResponse> getAllProduits() {
        return produitService.getAllProduits();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduit(@PathVariable String id) {
        produitService.deleteProduit(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateProduit(@PathVariable String id, @RequestBody ProduitRequest produitRequest) {
        produitService.updateProduit(id, produitRequest);
    }
}

