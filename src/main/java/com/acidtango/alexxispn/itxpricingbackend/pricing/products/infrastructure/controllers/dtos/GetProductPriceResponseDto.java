package com.acidtango.alexxispn.itxpricingbackend.pricing.products.infrastructure.controllers.dtos;

import java.time.Instant;

public record GetProductPriceResponseDto(
        String productCode, String brandCode, Instant fromDateTime, Instant toDateTime, double amount,
        String currencyCode
) {

    public ProductPriceResponseDto productPrice() {
        return new ProductPriceResponseDto(
                productCode,
                brandCode,
                fromDateTime,
                toDateTime,
                amount,
                currencyCode
        );
    }
}

