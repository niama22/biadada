package com.example.service_commande.dto;

import org.springframework.data.annotation.Id;

public class Produit {

    private String id;
    private String skuCode;
    private String libelle;
    private String description;
    private double prix;
    private Integer quantite;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public Produit() {
    }

    public Produit(String id, String libelle, String skuCode, String description, double prix, Integer quantite) {
        this.id = id;
        this.libelle = libelle;
        this.skuCode = skuCode;
        this.description = description;
        this.prix = prix;
        this.quantite = quantite;
    }
}