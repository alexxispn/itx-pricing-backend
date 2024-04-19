package com.acidtango.alexxispn.itxpricingbackend.pricing.products.application;

import com.acidtango.alexxispn.itxpricingbackend.pricing.products.domain.*;
import com.acidtango.alexxispn.itxpricingbackend.pricing.shared.application.UseCase;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class FindProductPriceFromDate extends UseCase {

    public FindProductPriceFromDate() {
    }

    public ProductPrice execute(String brandCode, String productCode, LocalDateTime dateTime) {
        return ProductPrice.create(
                new ProductPriceId(UUID.randomUUID().toString()),
                new ProductCode(productCode),
                new BrandCode(brandCode),
                new RangeDateTime(LocalDateTime.of(2020, 6, 14, 0, 0, 0), LocalDateTime.of(2020, 12, 31, 23, 59, 59)),
                new Price(35.50, "EUR"),
                new Priority(0)
        );
    }
}
