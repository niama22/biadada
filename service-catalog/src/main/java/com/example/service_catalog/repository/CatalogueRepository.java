package com.example.service_catalog.repository;

import com.example.service_catalog.model.Catalogue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CatalogueRepository extends JpaRepository<Catalogue, Long> {
    Optional<Catalogue> findBySkuCode(String skuCode);
    List<Catalogue> findBySkuCodeIn(List<String> skuCode);
}

