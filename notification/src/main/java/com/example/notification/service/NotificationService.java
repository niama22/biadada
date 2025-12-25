package com.example.notification.service;

import com.example.notification.event.CommandeEtablieEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {

    private final JavaMailSender javaMailSender;

    @KafkaListener(topics = "commande_etablie", groupId = "service-notification")
    public void listen(CommandeEtablieEvent event) {

        log.info("Message reçu : {}", event);

        MimeMessagePreparator email = msg -> {
            MimeMessageHelper helper = new MimeMessageHelper(msg);
            helper.setFrom("springshop@email.com");
            helper.setTo(event.getEmail());
            helper.setSubject("Commande " + event.getNumCommande() + " établie");
            helper.setText(buildEmailBody(event));
        };

        try {
            javaMailSender.send(email);
            log.info("Email envoyé à {}", event.getEmail());
        } catch (Exception e) {
            log.error("Erreur lors de l'envoi de l'email", e);
        }
    }

    private String buildEmailBody(CommandeEtablieEvent event) {
        StringBuilder sb = new StringBuilder();
        sb.append("Bonjour ").append(event.getPrenom()).append(" ").append(event.getNom()).append("\n\n");
        sb.append("Votre commande N° ").append(event.getNumCommande()).append(" est établie avec succès.\n\n");
        sb.append("Détails de la commande:\n");
        sb.append("\nMerci pour votre confiance!");
        return sb.toString();
    }
}
