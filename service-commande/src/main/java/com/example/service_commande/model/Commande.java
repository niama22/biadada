package com.example.service_commande.model;


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

    @OneToMany(cascade = CascadeType.ALL)
    private List<ArticleLigneCommande> articleLigneCommande;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ArticleLigneCommande> getArticleLigneCommande() {
        return articleLigneCommande;
    }

    public void setArticleLigneCommande(List<ArticleLigneCommande> articleLigneCommande) {
        this.articleLigneCommande = articleLigneCommande;
    }

    public String getNumCommande() {
        return numCommande;
    }

    public void setNumCommande(String numCommande) {
        this.numCommande = numCommande;
    }
}
