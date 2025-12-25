package com.example.service_catalog.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CatalogueReponse {
    private String skuCode;
    private Boolean estEnStock;

    public CatalogueReponse(String skuCode, Boolean estEnStock) {
        this.skuCode = skuCode;
        this.estEnStock = estEnStock;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public Boolean getEstEnStock() {
        return estEnStock;
    }

    public void setEstEnStock(Boolean estEnStock) {
        this.estEnStock = estEnStock;
    }
}