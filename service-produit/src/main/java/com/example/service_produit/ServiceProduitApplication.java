package com.example.service_produit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import com.example.service_produit.repository.ProduitRepository;
import com.example.service_produit.model.Produit;

@SpringBootApplication
public class ServiceProduitApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceProduitApplication.class, args);
	}


}
