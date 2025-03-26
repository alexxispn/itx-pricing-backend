package com.acidtango.alexxispn.itxpricingbackend.pricing.products.infrastructure.repository;

import com.acidtango.alexxispn.itxpricingbackend.pricing.products.domain.*;
import com.acidtango.alexxispn.itxpricingbackend.pricing.products.infrastructure.repository.entities.ProductPriceEntity;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Profile("h2")
@Repository
public class H2ProductPriceRepository implements ProductPriceRepository {
    private final JpaProductPriceRepository jpaProductPriceRepository;

    public H2ProductPriceRepository(JpaProductPriceRepository jpaProductPriceRepository) {
        this.jpaProductPriceRepository = jpaProductPriceRepository;
    }

    @Override
    public List<ProductPrice> find(String productCode, String brandCode, Instant date) {
        List<ProductPriceEntity> productPriceEntities = jpaProductPriceRepository
                .findByProductCodeAndBrandCodeAndStartDateLessThanEqualAndEndDateGreaterThanEqual(productCode, brandCode, date, date);
        return productPriceEntities
                .stream()
                .map(ProductPriceEntity::toDomain)
                .toList();
    }
}
