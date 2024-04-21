package com.acidtango.alexxispn.itxpricingbackend.pricing.products.infrastructure.repository;

import com.acidtango.alexxispn.itxpricingbackend.pricing.products.infrastructure.repository.entities.ProductPriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.List;


public interface JpaProductPriceRepository extends JpaRepository<ProductPriceEntity, String> {
    List<ProductPriceEntity> findByProductCodeAndBrandCodeAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
            String productCode,
            String brandCode,
            Instant startDate,
            Instant endDate);
}
