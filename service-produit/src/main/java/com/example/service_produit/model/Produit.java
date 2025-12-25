package com.example.service_produit.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("produit")
public class Produit {
    @Id
    private String id;
    private String libelle;
    private String description;
    private double prix;
}
