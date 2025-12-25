package com.example.notification.event;

public class CommandeEtablieEvent {

    private String numCommande;
    private String nom;
    private String prenom;
    private String email;

    public CommandeEtablieEvent() {}

    public CommandeEtablieEvent(String numCommande, String nom, String prenom, String email) {
        this.numCommande = numCommande;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
    }

    public String getNumCommande() { return numCommande; }
    public void setNumCommande(String numCommande) { this.numCommande = numCommande; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    @Override
    public String toString() {
        return "CommandeEtablieEvent{" +
                "numCommande='" + numCommande + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}