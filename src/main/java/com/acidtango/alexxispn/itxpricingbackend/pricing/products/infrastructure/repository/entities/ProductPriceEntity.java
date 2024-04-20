package com.acidtango.alexxispn.itxpricingbackend.pricing.products.infrastructure.repository.entities;

import com.acidtango.alexxispn.itxpricingbackend.pricing.products.domain.ProductPrice;
import com.acidtango.alexxispn.itxpricingbackend.pricing.products.domain.ProductPricePrimitives;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ProductPriceEntity {
    @Id
    private String id;

    private String productCode;

    private String brandCode;

    private Double price;

    private String currency;

    private String startDate;

    private String endDate;

    private int priority;

    public ProductPrice toDomain() {
        ProductPricePrimitives primitives = new ProductPricePrimitives(
                id,
                productCode,
                brandCode,
                startDate,
                endDate,
                price,
                currency,
                priority
        );
        return ProductPrice.fromPrimitives(primitives);
    }

    public static ProductPriceEntity fromDomain(ProductPrice productPrice) {
        ProductPricePrimitives primitives = productPrice.toPrimitives();
        ProductPriceEntity entity = new ProductPriceEntity();

        entity.id = primitives.id();
        entity.productCode = primitives.productCode();
        entity.brandCode = primitives.brandCode();
        entity.startDate = primitives.fromDateTime();
        entity.endDate = primitives.toDateTime();
        entity.price = primitives.amount();
        entity.currency = primitives.currencyCode();
        entity.priority = primitives.priority();

        return entity;
    }
}
