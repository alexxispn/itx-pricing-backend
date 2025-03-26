package com.acidtango.alexxispn.itxpricingbackend.pricing.products.infrastructure.repository;

import com.acidtango.alexxispn.itxpricingbackend.pricing.products.domain.ProductPrice;
import com.acidtango.alexxispn.itxpricingbackend.pricing.products.domain.ProductPriceRepository;

import java.time.Instant;
import java.util.Collections;
import java.util.List;

public class NoMatchProductPriceRepository implements ProductPriceRepository {
    @Override
    public List<ProductPrice> find(String productCode, String brandCode, Instant date) {
        return Collections.emptyList();
    }
}

