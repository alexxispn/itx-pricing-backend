package com.acidtango.alexxispn.itxpricingbackend.pricing.products.infrastructure.controllers.dtos;

import java.time.Instant;

public record ProductPriceResponseDto(String productCode, String brandCode, Instant fromDateTime,
                                      Instant toDateTime, double amount, String currencyCode) {
}
