package com.acidtango.alexxispn.itxpricingbackend.pricing.products.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProductPriceShould {

    @Test
    public void match_product_code_brand_code_and_date() {
        ProductPrice productPrice = ProductPrice.create(
                new ProductPriceId(UUID.randomUUID().toString()),
                new ProductCode("1"),
                new BrandCode("1"),
                new RangeDateTime(LocalDateTime.of(2021, 1, 1, 0, 0, 0), LocalDateTime.of(2021, 12, 31, 23, 59, 59)),
                new Price(100, "USD"),
                new Priority(1)
        );

        assertTrue(productPrice.matches("1", "1", LocalDateTime.of(2021, 1, 1, 0, 0, 0).toString()));
    }

    @Test
    public void not_match_product_code_brand_code_and_date() {
        ProductPrice productPrice = ProductPrice.create(
                new ProductPriceId(UUID.randomUUID().toString()),
                new ProductCode("1"),
                new BrandCode("1"),
                new RangeDateTime(LocalDateTime.of(2021, 1, 1, 0, 0, 0), LocalDateTime.of(2021, 12, 31, 23, 59, 59)),
                new Price(100, "USD"),
                new Priority(1)
        );

        assertFalse(productPrice.matches("2", "1", LocalDateTime.of(2021, 1, 1, 0, 0, 0).toString()));
    }
}
