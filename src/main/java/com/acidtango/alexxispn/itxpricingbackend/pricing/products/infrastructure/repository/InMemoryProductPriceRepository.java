package com.acidtango.alexxispn.itxpricingbackend.pricing.products.infrastructure.repository;

import com.acidtango.alexxispn.itxpricingbackend.pricing.products.domain.*;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Profile("memory")
@Component
public class InMemoryProductPriceRepository implements ProductPriceRepository {
    private final List<ProductPrice> productPrices;

    public InMemoryProductPriceRepository() {
        productPrices = new ArrayList<>();
    }

    @Override
    public List<ProductPrice> find(String productCode, String brandCode, Instant date) {
        return productPrices.stream()
                .filter(productPrice -> productPrice.matches(productCode, brandCode, date))
                .toList();
    }

    public void save(ProductPrice productPrice) {
        productPrices.add(productPrice);
    }
}

