package com.acidtango.alexxispn.itxpricingbackend.pricing.products.infrastructure.controllers.dtos;

import com.acidtango.alexxispn.itxpricingbackend.pricing.products.domain.ProductPrice;

import java.time.Instant;

public record GetProductPriceResponseDto(
        String productCode, String brandCode, Instant fromDateTime, Instant toDateTime, double amount,
        String currencyCode
) {
    public static GetProductPriceResponseDto fromDomain(ProductPrice productPrice) {
        var primitives = productPrice.toPrimitives();
        return new GetProductPriceResponseDto(
                primitives.productCode(),
                primitives.brandCode(),
                primitives.fromDateTime(),
                primitives.toDateTime(),
                primitives.amount(),
                primitives.currencyCode()
        );
    }

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

