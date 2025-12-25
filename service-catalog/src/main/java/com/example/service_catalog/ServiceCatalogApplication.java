package com.example.service_catalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.service_catalog.model.Catalogue;
import com.example.service_catalog.repository.CatalogueRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class ServiceCatalogApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceCatalogApplication.class, args);
	}

	@Bean
    CommandLineRunner commandLineRunner(CatalogueRepository repo) {
        return args -> {
            Catalogue c1 = new Catalogue();
            c1.setSkuCode("iphone_13");
            c1.setQuantite(100);
            c1.setPrix(699.0);

            Catalogue c2 = new Catalogue();
            c2.setSkuCode("galaxy_s21");
            c2.setQuantite(0); // out of stock
            c2.setPrix(599.0);

            repo.save(c1);
            repo.save(c2);
        };
    }

}
