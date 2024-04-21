package com.acidtango.alexxispn.itxpricingbackend.pricing.products.domain;

import java.time.Instant;

public record ProductPricePrimitives(
        String id,
        String productCode,
        String brandCode,
        Instant fromDateTime,
        Instant toDateTime,
        double amount,
        String currencyCode,
        int priority
){}
