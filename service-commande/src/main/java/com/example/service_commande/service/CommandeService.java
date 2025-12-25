package com.example.service_commande.service;

import com.example.service_commande.dto.ArticleLigneCommandeDto;
import com.example.service_commande.dto.CatalogueReponse;
import com.example.service_commande.dto.CommandeRequest;
import com.example.service_commande.model.ArticleLigneCommande;
import com.example.service_commande.model.Commande;
import com.example.service_commande.repository.CommandeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.kafka.core.KafkaTemplate;
import com.example.service_commande.event.CommandeEtablieEvent;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service

@Transactional
public class CommandeService {
        @Autowired
        private  CommandeRepository commandeRepository;
        @Autowired
        private  WebClient webClient;
        @Autowired
        private  KafkaTemplate<String, CommandeEtablieEvent> kafkaTemplate;

        public void etablirCommande(CommandeRequest commandeRequest) {
                Commande commande = new Commande();
                commande.setNumCommande(UUID.randomUUID().toString());

                List<ArticleLigneCommande> articleLigneCommande = commandeRequest
                                .getArticleLigneCommandeDtoList()
                                .stream()
                                .map(this::mapDto)
                                .toList();

                commande.setArticleLigneCommande(articleLigneCommande);

                List<String> skuCodes = commande.getArticleLigneCommande()
                                .stream()
                                .map(ArticleLigneCommande::getSkucode)
                                .toList();

                CatalogueReponse[] catalogueReponsesArray = webClient.get()
                                .uri("http://localhost:8082/api/catalogue",
                                                uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
                                .retrieve()
                                .bodyToMono(CatalogueReponse[].class)
                                .block();

                boolean tousProduitEnStock = Arrays.stream(catalogueReponsesArray)
                                .allMatch(CatalogueReponse::getEstEnStock);

                if (tousProduitEnStock) {
                        commandeRepository.save(commande);

                        // Map product details for email notification

                        CommandeEtablieEvent event = new CommandeEtablieEvent(
                                        commande.getNumCommande(),
                                        commandeRequest.getNom(),
                                        commandeRequest.getPrenom(),
                                        commandeRequest.getEmail());

                        kafkaTemplate.send("commande_etablie", event);
                } else {
                        throw new IllegalArgumentException("Produit n'existe pas en stocke");
                }
        }

        private ArticleLigneCommande mapDto(ArticleLigneCommandeDto dto) {
                ArticleLigneCommande a = new ArticleLigneCommande();
                a.setId(dto.getId());
                a.setPrix(dto.getPrix());
                a.setSkucode(dto.getSkucode());
                a.setQuantite(dto.getQuantite());
                return a;
        }
}
