package com.acidtango.alexxispn.itxpricingbackend.pricing.products.domain;

import com.acidtango.alexxispn.itxpricingbackend.pricing.shared.domain.AggregateRoot;

import java.time.LocalDateTime;

public class ProductPrice extends AggregateRoot {
    private final ProductPriceId id;
    private final ProductCode productCode;
    private final BrandCode brandCode;
    private final RangeDateTime rangeDateTime;
    private final Price price;
    private final Priority priority;

    public static ProductPrice create(
            ProductPriceId id,
            ProductCode productCode,
            BrandCode brandCode,
            RangeDateTime rangeDateTime,
            Price price,
            Priority priority
    ) {
        return new ProductPrice(id, productCode, brandCode, rangeDateTime, price, priority);
    }

    private ProductPrice(ProductPriceId id, ProductCode productCode, BrandCode brandCode, RangeDateTime rangeDateTime,
                         Price price, Priority priority) {
        this.id = id;
        this.productCode = productCode;
        this.brandCode = brandCode;
        this.rangeDateTime = rangeDateTime;
        this.price = price;
        this.priority = priority;
    }

    public static ProductPrice fromPrimitives(ProductPricePrimitives primitives) {
        return new ProductPrice(
                new ProductPriceId(primitives.id()),
                new ProductCode(primitives.productCode()),
                new BrandCode(primitives.brandCode()),
                new RangeDateTime(LocalDateTime.parse(primitives.fromDateTime()), LocalDateTime.parse(primitives.toDateTime())),
                new Price(primitives.amount(), primitives.currencyCode()),
                new Priority(primitives.priority())
        );
    }

    public ProductPricePrimitives toPrimitives() {
        return new ProductPricePrimitives(
                id.value(),
                productCode.value(),
                brandCode.value(),
                rangeDateTime.fromDateTime(),
                rangeDateTime.toDateTime(),
                price.value(),
                price.currency(),
                priority.value()
        );
    }

    public boolean matches(String productCode, String brandCode, String date) {
        return this.productCode.value().equals(productCode) && this.brandCode.value().equals(brandCode) && this.rangeDateTime.contains(date);
    }

    public int priority() {
        return priority.value();
    }
}

