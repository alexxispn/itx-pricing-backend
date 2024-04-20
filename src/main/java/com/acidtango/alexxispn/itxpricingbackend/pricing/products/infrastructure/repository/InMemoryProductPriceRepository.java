package com.acidtango.alexxispn.itxpricingbackend.pricing.products.infrastructure.repository;

import com.acidtango.alexxispn.itxpricingbackend.pricing.products.domain.*;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@Profile("memory")
public class InMemoryProductPriceRepository implements ProductPriceRepository {
    private final List<ProductPrice> productPrices;

    public InMemoryProductPriceRepository() {
        productPrices = new ArrayList<>();
        productPrices.add(ProductPrice.create(
                new ProductPriceId(UUID.randomUUID().toString()),
                new ProductCode("35455"),
                new BrandCode("1"),
                new RangeDateTime(LocalDateTime.of(2020, 6, 14, 0, 0, 0), LocalDateTime.of(2020, 12, 31, 23, 59, 59)),
                new Price(35.50, "EUR"),
                new Priority(0)
        ));
        productPrices.add(ProductPrice.create(
                new ProductPriceId(UUID.randomUUID().toString()),
                new ProductCode("35455"),
                new BrandCode("1"),
                new RangeDateTime(LocalDateTime.of(2020, 6, 14, 15, 0, 0), LocalDateTime.of(2020, 6, 14, 18, 30, 0)),
                new Price(25.45, "EUR"),
                new Priority(1)
        ));
        productPrices.add(ProductPrice.create(
                new ProductPriceId(UUID.randomUUID().toString()),
                new ProductCode("35455"),
                new BrandCode("1"),
                new RangeDateTime(LocalDateTime.of(2020, 6, 15, 0, 0, 0), LocalDateTime.of(2020, 6, 15, 11, 0, 0)),
                new Price(30.50, "EUR"),
                new Priority(1)
        ));
        productPrices.add(ProductPrice.create(
                new ProductPriceId(UUID.randomUUID().toString()),
                new ProductCode("35455"),
                new BrandCode("1"),
                new RangeDateTime(LocalDateTime.of(2020, 6, 15, 16, 0, 0), LocalDateTime.of(2020, 12, 31, 23, 59, 59)),
                new Price(38.95, "EUR"),
                new Priority(1)
        ));
    }

    @Override
    public ProductPrice find(String productCode, String brandCode, String date) {
        return productPrices.stream()
                .filter(productPrice -> productPrice.matches(productCode, brandCode, date))
                .sorted((p1, p2) -> p2.priority() - p1.priority())
                .findFirst()
                .orElse(null);
    }

    @Override
    public void save(ProductPrice productPrice) {
        productPrices.add(productPrice);
    }
}

