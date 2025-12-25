package com.example.service_commande.model;


import com.example.service_commande.dto.Produit;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "t_commande")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Commande {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String numCommande;

    @Transient
    private List<Produit> Produits;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getNumCommande() {
        return numCommande;
    }

    public void setNumCommande(String numCommande) {
        this.numCommande = numCommande;
    }
    public List<Produit> getProduits() {
        return Produits;
    }
    public void setProduits(List<Produit> Produits) {
        this.Produits = Produits;
    }

}
