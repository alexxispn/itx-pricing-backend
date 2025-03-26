package com.acidtango.alexxispn.itxpricingbackend.pricing.products.domain;

import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProductPriceTest {
    ProductPrice productPrice = ProductPrice.create(
            new ProductPriceId(UUID.randomUUID().toString()),
            new ProductCode("1"),
            new BrandCode("1"),
            new RangeDateTime(Instant.parse("2021-01-01T00:00:00Z"), Instant.parse("2021-12-31T23:59:59Z")),
            new Price(100, "USD"),
            new Priority(1)
    );

    @Test
    public void match_product_code_brand_code_and_date() {
        assertTrue(productPrice.matches("1", "1", Instant.parse("2021-01-01T00:00:00Z")));
    }

    @Test
    public void not_match_product_code_brand_code_and_date() {
        assertFalse(productPrice.matches("2", "1", Instant.parse("2021-01-01T00:00:00Z")));
    }
}
