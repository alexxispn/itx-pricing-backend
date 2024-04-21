package com.acidtango.alexxispn.itxpricingbackend.pricing.products.infrastructure.repository;

import com.acidtango.alexxispn.itxpricingbackend.pricing.products.domain.*;
import com.acidtango.alexxispn.itxpricingbackend.pricing.products.infrastructure.repository.entities.ProductPriceEntity;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Profile("h2")
@Repository
public class H2ProductPriceRepository implements ProductPriceRepository {
    private final JpaProductPriceRepository jpaProductPriceRepository;

    public H2ProductPriceRepository(JpaProductPriceRepository jpaProductPriceRepository) {
        this.jpaProductPriceRepository = jpaProductPriceRepository;
        seeder();
    }

    @Override
    public List<ProductPrice> find(String productCode, String brandCode, Instant date) {
        List<ProductPriceEntity> productPriceEntities = jpaProductPriceRepository.findByProductCodeAndBrandCodeAndStartDateLessThanEqualAndEndDateGreaterThanEqual(productCode, brandCode, date, date);
        return productPriceEntities
                .stream()
                .map(ProductPriceEntity::toDomain)
                .toList();
    }

    private void seeder() {
        jpaProductPriceRepository.save(ProductPriceEntity.fromDomain(ProductPrice.create(
                new ProductPriceId(UUID.randomUUID().toString()),
                new ProductCode("35455"),
                new BrandCode("1"),
                new RangeDateTime(Instant.parse("2020-06-14T00:00:00Z"), Instant.parse("2020-12-31T23:59:59Z")),
                new Price(35.50, "EUR"),
                new Priority(0)
        )));
        jpaProductPriceRepository.save(ProductPriceEntity.fromDomain(ProductPrice.create(
                new ProductPriceId(UUID.randomUUID().toString()),
                new ProductCode("35455"),
                new BrandCode("1"),
                new RangeDateTime(Instant.parse("2020-06-14T15:00:00Z"), Instant.parse("2020-06-14T18:30:00Z")),
                new Price(25.45, "EUR"),
                new Priority(1)
        )));
        jpaProductPriceRepository.save(ProductPriceEntity.fromDomain(ProductPrice.create(
                new ProductPriceId(UUID.randomUUID().toString()),
                new ProductCode("35455"),
                new BrandCode("1"),
                new RangeDateTime(Instant.parse("2020-06-15T00:00:00Z"), Instant.parse("2020-06-15T11:00:00Z")),
                new Price(30.50, "EUR"),
                new Priority(1)
        )));
        jpaProductPriceRepository.save(ProductPriceEntity.fromDomain(ProductPrice.create(
                new ProductPriceId(UUID.randomUUID().toString()),
                new ProductCode("35455"),
                new BrandCode("1"),
                new RangeDateTime(Instant.parse("2020-06-15T16:00:00Z"), Instant.parse("2020-12-31T23:59:59Z")),
                new Price(38.95, "EUR"),
                new Priority(1)
        )));
    }
}
