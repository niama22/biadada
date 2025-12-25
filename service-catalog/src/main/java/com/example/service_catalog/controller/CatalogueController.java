package com.example.service_catalog.controller;

import com.example.service_catalog.dto.CatalogueReponse;
import com.example.service_catalog.service.CatalogueService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/catalogue")
public class CatalogueController {

    @Autowired
    private CatalogueService catalogueService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CatalogueReponse> estEnStocke(@RequestParam List<String> skuCode) {
        return catalogueService.estEnStocke(skuCode);
    }
}
