package com.acidtango.alexxispn.itxpricingbackend.pricing.products.domain;

import com.acidtango.alexxispn.itxpricingbackend.pricing.shared.domain.AggregateRoot;

public class ProductPrice extends AggregateRoot {
    private final ProductPriceId id;
    private final ProductCode productCode;
    private final BrandCode brandCode;
    private final RangeDateTime rangeDateTime;
    private final Amount amount;
    private final CurrencyCode currencyCode;
    private final Priority priority;

    public static ProductPrice create(
            ProductPriceId id,
            ProductCode productCode,
            BrandCode brandCode,
            RangeDateTime rangeDateTime,
            Amount amount,
            CurrencyCode currencyCode,
            Priority priority
    ) {
        return new ProductPrice(id, productCode, brandCode, rangeDateTime, amount, currencyCode, priority);
    }

    private ProductPrice(ProductPriceId id, ProductCode productCode, BrandCode brandCode, RangeDateTime rangeDateTime,
                         Amount amount, CurrencyCode currencyCode, Priority priority) {
        this.id = id;
        this.productCode = productCode;
        this.brandCode = brandCode;
        this.rangeDateTime = rangeDateTime;
        this.amount = amount;
        this.currencyCode = currencyCode;
        this.priority = priority;
    }

    public ProductPricePrimitives toPrimitives() {
        return new ProductPricePrimitives(
                id.value(),
                productCode.value(),
                brandCode.value(),
                rangeDateTime.fromDateTime(),
                rangeDateTime.toDateTime(),
                amount.value(),
                currencyCode.value(),
                priority.value()
        );
    }
}

