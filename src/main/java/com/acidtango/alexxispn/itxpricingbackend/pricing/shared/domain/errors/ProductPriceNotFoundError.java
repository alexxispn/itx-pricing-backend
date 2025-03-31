package com.acidtango.alexxispn.itxpricingbackend.pricing.shared.domain.errors;

public final class ProductPriceNotFoundError extends DomainError {
    public ProductPriceNotFoundError(String productCode, String brandCode) {
        super("No se encontró el precio para el producto " + productCode + " y marca " + brandCode, DomainErrorCode.NOT_FOUND);
    }
}


