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
                new FromDateTime("2020-06-14T00:00:00"),
                new ToDateTime("2020-12-31T23:59:59"),
                new Amount(35.5),
                new CurrencyCode("EUR")
        );
    }
}
