package com.acidtango.alexxispn.itxpricingbackend.pricing.products.infrastructure.repository;

import com.acidtango.alexxispn.itxpricingbackend.pricing.products.domain.ProductPrice;
import com.acidtango.alexxispn.itxpricingbackend.pricing.products.domain.ProductPriceRepository;

import java.time.Instant;
import java.util.List;

public class AlwaysMatchesProductPriceRepository implements ProductPriceRepository {
    private final ProductPrice[] productPrices;

    public AlwaysMatchesProductPriceRepository(ProductPrice[] productPrices) {
        this.productPrices = productPrices;
    }

    @Override
    public List<ProductPrice> find(String productCode, String brandCode, Instant date) {
        return List.of(productPrices);
    }
}
