package com.acidtango.alexxispn.itxpricingbackend.pricing.products.domain;

public record ProductPricePrimitives(
        String id,
        String productCode,
        String brandCode,
        String fromDateTime,
        String toDateTime,
        double amount,
        String currencyCode,
        int priority
){}
