package com.example.service_produit;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.service_produit.DTO.ProduitRequest;
import com.example.service_produit.repository.ProduitRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
public class ServiceProduitApplicationTests {

    @Container
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:7.0.11");

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ProduitRepository produitRepository;

    @Test
    void shouldCreateProduit() throws Exception {
        ProduitRequest produitRequest = ProduitRequest.builder()
                .libelle("TV")
                .description("Télévision Samsung 50 pouces")
                .prix(5000.0)
                .build();

        String produitJson = objectMapper.writeValueAsString(produitRequest);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/produits")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(produitJson))
                .andExpect(status().isCreated());

        Assertions.assertEquals(1, produitRepository.findAll().size());
    }
    @Test
    void shouldGetAllProduits() throws Exception {
        ProduitRequest produit = ProduitRequest.builder()
                .libelle("Chaise")
                .description("Chaise en bois")
                .prix(120.0)
                .build();

        produitRepository.save(com.example.service_produit.model.Produit.builder()
                .libelle(produit.getLibelle())
                .description(produit.getDescription())
                .prix(produit.getPrix())
                .build());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/produits"))
                .andExpect(status().isOk());
    }

}
