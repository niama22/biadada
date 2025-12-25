package com.example.service_produit.DTO;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProduitResponse {

    private String id;
    private String libelle;
    private String description;
    private Double prix;
    private String skuCode;
    private Integer quantite;
}
