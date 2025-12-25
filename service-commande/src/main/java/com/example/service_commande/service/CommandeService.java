package com.example.service_commande.service;

import com.example.service_commande.dto.CommandeRequest;
import com.example.service_commande.dto.Produit;

import com.example.service_commande.model.Commande;
import com.example.service_commande.repository.CommandeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@Slf4j

public class CommandeService {

        @Autowired
        private  CommandeRepository commandeRepository;
        @Autowired
        private  WebClient webClient;
//        @Autowired
//        private  KafkaTemplate<String, CommandeEtablieEvent> kafkaTemplate;
        public void etablirCommande(CommandeRequest request) {
                // Liste finale des produits commandés
                List<Produit> produitsCommandes = request.getSkuCodes().stream().map(skuCode -> {
                        // 1️⃣ Récupérer le produit via WebClient
                        Produit produit = webClient.get()
                                .uri("/api/produits/{skuCode}", skuCode)
                                .retrieve()
                                .bodyToMono(Produit.class)
                                .block();

                        if (produit == null) {
                                throw new IllegalArgumentException("Produit avec SKU " + skuCode + " n'existe pas");
                        }

                        if (produit.getQuantite() == null || produit.getQuantite() <= 0) {
                                throw new IllegalArgumentException("Produit " + skuCode + " en rupture de stock");
                        }

                        // 2️⃣ Décrémenter la quantité via WebClient
                        webClient.post()
                                .uri(uriBuilder -> uriBuilder
                                        .path("/api/produits/{skuCode}/decrement")
                                        .queryParam("quantiteCommande", 1) // ou selon ta logique
                                        .build(skuCode))
                                .retrieve()
                                .bodyToMono(String.class)
                                .block();

                        return produit;
                }).toList();

                // Vérification que tous les produits ont bien été récupérés
                if (produitsCommandes.size() != request.getSkuCodes().size()) {
                        throw new IllegalArgumentException("Un ou plusieurs produits n'existent pas ou sont en rupture de stock");
                }

                // 3️⃣ Créer la commande
                Commande commande = new Commande();
                commande.setNumCommande(UUID.randomUUID().toString());
                commande.setProduits(produitsCommandes);

                commandeRepository.save(commande);

//                // 4️⃣ Envoyer l'événement Kafka
//                CommandeEtablieEvent event = new CommandeEtablieEvent(
//                        commande.getNumCommande(),
//                        request.getNom(),
//                        request.getPrenom(),
//                        request.getEmail()
//                );
//
//                kafkaTemplate.send("commande_etablie", event);


        }

}
