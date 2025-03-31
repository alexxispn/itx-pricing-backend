package com.acidtango.alexxispn.itxpricingbackend.pricing.products.infrastructure.repository;

import com.acidtango.alexxispn.itxpricingbackend.pricing.products.domain.ProductPrice;
import com.acidtango.alexxispn.itxpricingbackend.pricing.products.domain.ProductPriceRepository;

import java.time.Instant;
import java.util.List;

public class AlwaysMatchesProductPriceRepository implements ProductPriceRepository {
    private final List<ProductPrice> productPrices;

    public AlwaysMatchesProductPriceRepository(List<ProductPrice> productPrices) {
        this.productPrices = productPrices;
    }

    @Override
    public List<ProductPrice> find(String productCode, String brandCode, Instant date) {
        return productPrices;
    }
}
