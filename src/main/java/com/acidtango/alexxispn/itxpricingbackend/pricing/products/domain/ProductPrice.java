package com.acidtango.alexxispn.itxpricingbackend.pricing.products.domain;

import com.acidtango.alexxispn.itxpricingbackend.pricing.shared.domain.AggregateRoot;

public class ProductPrice extends AggregateRoot {
    private final ProductPriceId id;
    private final ProductCode productCode;
    private final BrandCode brandCode;
    private final FromDateTime fromDateTime;
    private final ToDateTime toDateTime;
    private final Amount amount;
    private final CurrencyCode currencyCode;

    public static ProductPrice create(ProductPriceId id, ProductCode productCode, BrandCode brandCode, FromDateTime fromDateTime,
                                      ToDateTime toDateTime, Amount amount, CurrencyCode currencyCode) {
        return new ProductPrice(id, productCode, brandCode, fromDateTime, toDateTime, amount, currencyCode);
    }

    private ProductPrice(ProductPriceId id, ProductCode productCode, BrandCode brandCode, FromDateTime fromDateTime, ToDateTime toDateTime,
                         Amount amount, CurrencyCode currencyCode) {
        this.id = id;
        this.productCode = productCode;
        this.brandCode = brandCode;
        this.fromDateTime = fromDateTime;
        this.toDateTime = toDateTime;
        this.amount = amount;
        this.currencyCode = currencyCode;
    }

    public ProductPricePrimitives toPrimitives() {
        return new ProductPricePrimitives(
                id.value(),
                productCode.value(),
                brandCode.value(),
                fromDateTime.value(),
                toDateTime.value(),
                amount.value(),
                currencyCode.value()
        );
    }
}

