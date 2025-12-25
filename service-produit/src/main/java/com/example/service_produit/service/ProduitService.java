package com.example.service_produit.service;

import com.example.service_produit.DTO.*;
import com.example.service_produit.model.Produit;
import com.example.service_produit.repository.ProduitRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProduitService {
    private final ProduitRepository produitRepository;

    public void createProduit(ProduitRequest produitRequest) {
        Produit produit = Produit.builder()
                .libelle(produitRequest.getLibelle())
                .description(produitRequest.getDescription())
                .prix(produitRequest.getPrix())
                .build();

        produitRepository.save(produit);
        log.info("Produit enregistré : {}", produit.getId());
    }

    public List<ProduitResponse> getAllProduits() {
        return produitRepository.findAll()
                .stream()
                .map(this::mapToProduitResponse)
                .toList();
    }

    private ProduitResponse mapToProduitResponse(Produit produit) {
        return ProduitResponse.builder()
                .id(produit.getId())
                .libelle(produit.getLibelle())
                .description(produit.getDescription())
                .prix(produit.getPrix())
                .build();
    }

    public void deleteProduit(String id) {
        produitRepository.deleteById(id);
        log.info("Produit supprimé : {}", id);
    }

    public void updateProduit(String id, ProduitRequest request) {
        Produit produit = produitRepository.findById(id).orElseThrow();
        produit.setLibelle(request.getLibelle());
        produit.setDescription(request.getDescription());
        produit.setPrix(request.getPrix());
        produitRepository.save(produit);
        log.info("Produit mis à jour : {}", id);
    }
}
