package com.example.service_produit.service;

import com.example.service_produit.DTO.*;
import com.example.service_produit.model.Produit;
import com.example.service_produit.repository.ProduitRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProduitService {

    @Autowired
    private  ProduitRepository produitRepository;

    // ================= CREATE =================
    public void createProduit(ProduitRequest request) {

        produitRepository.findBySkuCode(request.getSkuCode())
                .ifPresentOrElse(
                        produitExistant -> {
                            // ➕ augmenter quantité
                            produitExistant.setQuantite(
                                    produitExistant.getQuantite() + request.getQuantite()
                            );
                            produitRepository.save(produitExistant);
                            log.info("Quantité augmentée skuCode={}", request.getSkuCode());
                        },
                        () -> {
                            // 🆕 nouveau produit
                            Produit produit = Produit.builder()
                                    .skuCode(request.getSkuCode())
                                    .libelle(request.getLibelle())
                                    .description(request.getDescription())
                                    .prix(request.getPrix())
                                    .quantite(request.getQuantite())
                                    .build();
                            produitRepository.save(produit);
                            log.info("Produit créé skuCode={}", request.getSkuCode());
                        }
                );
    }

    // ================= READ =================
    public List<ProduitResponse> getAllProduits() {
        return produitRepository.findAll()
                .stream()
                .map(this::mapToProduitResponse)
                .toList();
    }

    // ================= UPDATE =================
    public void updateProduit(String id, ProduitRequest request) {

        Produit produit = produitRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produit introuvable"));

        produit.setLibelle(request.getLibelle());
        produit.setDescription(request.getDescription());
        produit.setPrix(request.getPrix());

        produitRepository.save(produit);
        log.info("Produit mis à jour id={}", id);
    }

    // ================= DELETE (-1 quantité) =================
    public void deleteProduit(String skuCode) {

        Produit produit = produitRepository.findBySkuCode(skuCode)
                .orElseThrow(() -> new RuntimeException("Produit introuvable"));

        int nouvelleQuantite = produit.getQuantite() - 1;

        if (nouvelleQuantite <= 0) {
            produitRepository.delete(produit);
            log.info("Produit supprimé skuCode={}", skuCode);
        } else {
            produit.setQuantite(nouvelleQuantite);
            produitRepository.save(produit);
            log.info("Quantité décrémentée skuCode={}, quantite={}", skuCode, nouvelleQuantite);
        }
    }

    // ================= MAPPER =================
    private ProduitResponse mapToProduitResponse(Produit produit) {
        return ProduitResponse.builder()
                .id(produit.getId())
                .skuCode(produit.getSkuCode())
                .libelle(produit.getLibelle())
                .description(produit.getDescription())
                .prix(produit.getPrix())
                .quantite(produit.getQuantite())
                .build();
    }
}
