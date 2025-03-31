package com.acidtango.alexxispn.itxpricingbackend.pricing.products.infrastructure.repository.entities;

import com.acidtango.alexxispn.itxpricingbackend.pricing.products.domain.ProductPrice;
import com.acidtango.alexxispn.itxpricingbackend.pricing.products.domain.ProductPricePrimitives;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.Instant;

@Entity
public class ProductPriceEntity {
    @Id
    private String id;

    private String productCode;

    private String brandCode;

    private Double price;

    private String currency;

    private Instant startDate;

    private Instant endDate;

    private int priority;

    public static ProductPrice toDomain(ProductPriceEntity entity) {
        ProductPricePrimitives primitives = new ProductPricePrimitives(
                entity.id,
                entity.productCode,
                entity.brandCode,
                entity.startDate,
                entity.endDate,
                entity.price,
                entity.currency,
                entity.priority
        );

        return ProductPrice.fromPrimitives(primitives);
    }

}
