package com.example.service_commande.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data

public class CommandeRequest {

    private List<String> skuCodes;
    private String nom;
    private String prenom;
    private String email;

    public List<String> getSkuCodes() {
        return skuCodes;
    }

    public void setSkuCodes(List<String> skuCodes) {
        this.skuCodes = skuCodes;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public CommandeRequest() {
    }

    public CommandeRequest(List<String> skuCodes, String nom, String prenom, String email) {
        this.skuCodes = skuCodes;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
    }
}
