package com.example.service_produit.repository;

import com.example.service_produit.model.Produit;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ProduitRepository extends MongoRepository<Produit, String> {
    Optional<Produit> findBySkuCode(String skuCode);
}
