package com.example.notification.service;

import com.example.notification.event.CommandeEtablieEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationService {

    private final JavaMailSender mailSender;

    @KafkaListener(topics = "commande_etablie", groupId = "notification-group")
    public void listen(CommandeEtablieEvent event) {
        System.out.println("Received event: " + event);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(event.getEmail()); // Email du client
        message.setSubject("Nouvelle commande reçue !");
        message.setText("Bonjour " + event.getPrenom() + " " + event.getNom() + ",\n\n" +
                "Votre commande (" + event.getNumCommande() + ") a été enregistrée avec succès.\n\nMerci !");

        try {
            mailSender.send(message);
            System.out.println("Email envoyé !");
        } catch (Exception e) {
            log.error("Erreur lors de l'envoi de l'email", e);
        }
    }


}
