package com.example.service_commande.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.util.List;


public class CommandeEtablieEvent {
    private String numCommande;
    private String nom;
    private String prenom;
    private String email;

    public CommandeEtablieEvent() {
    }

    public CommandeEtablieEvent(String numCommande, String nom, String prenom, String email) {
        this.numCommande = numCommande;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
    }

    public String getNumCommande() {
        return numCommande;
    }

    public void setNumCommande(String numCommande) {
        this.numCommande = numCommande;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
