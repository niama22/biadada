package com.example.service_commande.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CatalogueReponse {
    private String skuCode;
    private Boolean estEnStock;

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