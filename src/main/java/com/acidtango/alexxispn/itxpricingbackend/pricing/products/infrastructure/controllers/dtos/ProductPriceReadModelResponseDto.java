package com.acidtango.alexxispn.itxpricingbackend.pricing.products.infrastructure.controllers.dtos;

import java.time.LocalDateTime;

public record ProductPriceReadModelResponseDto(String id, String productCode, String brandCode, LocalDateTime fromDateTime,
                                               LocalDateTime toDateTime, double amount, String currencyCode) {
}
