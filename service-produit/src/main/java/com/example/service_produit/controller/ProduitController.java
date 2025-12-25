package com.example.service_produit.controller;

import com.example.service_produit.DTO.*;
import com.example.service_produit.model.Produit;
import com.example.service_produit.repository.ProduitRepository;
import com.example.service_produit.service.ProduitService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/produits")

public class ProduitController {
    @Autowired
    private  ProduitService produitService;

    @Autowired
    private ProduitRepository produitRepository;


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
    // 1️⃣ Récupérer un produit par SKU
    @GetMapping("/{skuCode}")
    public ResponseEntity<Produit> getProduitBySku(@PathVariable String skuCode) {
        Produit produit = produitRepository.findBySkuCode(skuCode)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Produit avec SKU " + skuCode + " non trouvé"
                ));
        return ResponseEntity.ok(produit);
    }
    @PostMapping("/{skuCode}/decrement")
    public ResponseEntity<String> decrementQuantite(@PathVariable String skuCode,
                                                    @RequestParam int quantiteCommande) {
        Produit produit = produitRepository.findBySkuCode(skuCode)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Produit avec SKU " + skuCode + " non trouvé"
                ));

        if (quantiteCommande <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Quantité commandée doit être > 0");
        }

        if (produit.getQuantite() == null || produit.getQuantite() < quantiteCommande) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Quantité insuffisante pour le produit " + skuCode
            );
        }

        produit.setQuantite(produit.getQuantite() - quantiteCommande);

        if (produit.getQuantite() <= 0) {
            produitRepository.delete(produit);
            return ResponseEntity.ok("Produit supprimé car la quantité est devenue 0");
        } else {
            produitRepository.save(produit);
            return ResponseEntity.ok("Quantité mise à jour avec succès. Nouvelle quantité=" + produit.getQuantite());
        }
    }

}

