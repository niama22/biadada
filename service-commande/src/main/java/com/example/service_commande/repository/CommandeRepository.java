package com.example.service_commande.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.example.service_commande.model.Commande;

public interface CommandeRepository extends JpaRepository<Commande, Long> {
}

