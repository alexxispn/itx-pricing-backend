package com.acidtango.alexxispn.itxpricingbackend.pricing.products.infrastructure.repository;

import com.acidtango.alexxispn.itxpricingbackend.pricing.products.doubles.SpyJpaProductPriceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class H2ProductPriceRepositoryTest {
    private SpyJpaProductPriceRepository jpaProductPriceRepository;
    private H2ProductPriceRepository h2ProductPriceRepository;

    @BeforeEach
    void setUp() {
        jpaProductPriceRepository = new SpyJpaProductPriceRepository();
        h2ProductPriceRepository = new H2ProductPriceRepository(jpaProductPriceRepository);
    }

    @Test
    public void find_product_prices() {
        String productCode = "productCode";
        String brandCode = "brandCode";
        Instant date = Instant.parse("2024-01-01T00:00:00Z");

        h2ProductPriceRepository.find(productCode, brandCode, date);

        assertEquals(productCode, jpaProductPriceRepository.getReceivedProductCode());
        assertEquals(brandCode, jpaProductPriceRepository.getReceivedBrandCode());
        assertEquals(date, jpaProductPriceRepository.getReceivedStartDate());
        assertEquals(date, jpaProductPriceRepository.getReceivedEndDate());
    }
}
