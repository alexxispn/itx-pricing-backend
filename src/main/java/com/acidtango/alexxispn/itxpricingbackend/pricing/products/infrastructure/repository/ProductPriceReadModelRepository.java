package com.acidtango.alexxispn.itxpricingbackend.pricing.products.infrastructure.repository;

public interface ProductPriceReadModelRepository {
    ProductPriceReadModel find(String productCode, String brandCode, String date);
}
