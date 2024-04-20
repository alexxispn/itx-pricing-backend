package com.acidtango.alexxispn.itxpricingbackend.pricing.products.infrastructure.repository;

public record ProductPriceReadModel(
        String id,
        String productCode,
        String brandCode,
        String fromDateTime,
        String toDateTime,
        double amount,
        String currencyCode
) {
}
