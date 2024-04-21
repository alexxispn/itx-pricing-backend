package com.acidtango.alexxispn.itxpricingbackend.pricing.products.infrastructure.repository;

import java.time.Instant;

public record ProductPriceReadModel(
        String id,
        String productCode,
        String brandCode,
        Instant fromDateTime,
        Instant toDateTime,
        double amount,
        String currencyCode
) {
}
