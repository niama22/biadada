package com.example.notification.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommandeEtablieEvent {
    private String numCommande;
    private String nom;
    private String prenom;
    private String email;

}
