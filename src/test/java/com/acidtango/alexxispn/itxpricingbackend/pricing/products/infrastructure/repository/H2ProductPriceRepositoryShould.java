package com.acidtango.alexxispn.itxpricingbackend.pricing.products.infrastructure.repository;

import com.acidtango.alexxispn.itxpricingbackend.pricing.products.infrastructure.repository.entities.ProductPriceEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.mockito.Mockito.*;

public class H2ProductPriceRepositoryShould {
    private JpaProductPriceRepository jpaProductPriceRepository;
    private H2ProductPriceRepository h2ProductPriceRepository;

    @BeforeEach
    void setUp() {
        jpaProductPriceRepository = mock(JpaProductPriceRepository.class);
        h2ProductPriceRepository = new H2ProductPriceRepository(jpaProductPriceRepository);
    }

    @Test
    public void invoke_seeder_on_initialization() {
        verify(jpaProductPriceRepository, times(4)).save(any(ProductPriceEntity.class));
    }

    @Test
    public void find_product_prices() {
        String productCode = "productCode";
        String brandCode = "brandCode";
        Instant date = Instant.parse("2024-01-01T00:00:00Z");

        h2ProductPriceRepository.find(productCode, brandCode, date);

        verify(jpaProductPriceRepository, times(1)).findByProductCodeAndBrandCodeAndStartDateLessThanEqualAndEndDateGreaterThanEqual(productCode, brandCode, date, date);
    }
}
