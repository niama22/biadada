package com.example.service_catalog.service;

import com.example.service_catalog.dto.CatalogueReponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.service_catalog.repository.CatalogueRepository;

import java.util.List;


@Service
public class CatalogueService {
     @Autowired
    private  CatalogueRepository catalogueRepository;

    @Transactional(readOnly = true)
    public List<CatalogueReponse> estEnStocke(List<String> skuCodes) {
        return catalogueRepository.findBySkuCodeIn(skuCodes).stream()
                .map(catalogue -> new CatalogueReponse(
                         catalogue.getSkuCode(),
                        catalogue.getQuantite() > 0))
                .toList();
    }
}
