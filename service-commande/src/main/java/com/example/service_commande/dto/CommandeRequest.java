package com.example.service_commande.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommandeRequest {
    private List<ArticleLigneCommandeDto> articleLigneCommandeDtoList;
    private String nom;
    private String prenom;
    private String email;

    public List<ArticleLigneCommandeDto> getArticleLigneCommandeDtoList() {
        return articleLigneCommandeDtoList;
    }

    public void setArticleLigneCommandeDtoList(List<ArticleLigneCommandeDto> articleLigneCommandeDtoList) {
        this.articleLigneCommandeDtoList = articleLigneCommandeDtoList;
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
}
