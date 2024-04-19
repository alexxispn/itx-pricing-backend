package com.acidtango.alexxispn.itxpricingbackend.pricing.products.infrastructure.controllers.dtos;

import com.acidtango.alexxispn.itxpricingbackend.pricing.products.domain.ProductPrice;

import java.time.LocalDateTime;

public record GetProductPriceResponseDto(
        String id, String productCode, String brandCode, String fromDateTime, String toDateTime, double amount,
        String currencyCode
) {
    public static GetProductPriceResponseDto fromDomain(ProductPrice productPrice) {
        var primitives = productPrice.toPrimitives();
        return new GetProductPriceResponseDto(
                primitives.id(),
                primitives.productCode(),
                primitives.brandCode(),
                primitives.fromDateTime(),
                primitives.toDateTime(),
                primitives.amount(),
                primitives.currencyCode()
        );
    }

    public ProductPriceReadModelResponseDto productPrice() {
        return new ProductPriceReadModelResponseDto(
                id,
                productCode,
                brandCode,
                LocalDateTime.parse(fromDateTime),
                LocalDateTime.parse(toDateTime),
                amount,
                currencyCode
        );
    }
}

