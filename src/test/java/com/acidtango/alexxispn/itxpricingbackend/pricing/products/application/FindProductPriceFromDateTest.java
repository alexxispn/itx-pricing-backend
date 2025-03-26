package com.acidtango.alexxispn.itxpricingbackend.pricing.products.application;

import com.acidtango.alexxispn.itxpricingbackend.pricing.products.domain.*;
import com.acidtango.alexxispn.itxpricingbackend.pricing.products.infrastructure.controllers.dtos.ProductPriceResponseDto;
import com.acidtango.alexxispn.itxpricingbackend.pricing.products.infrastructure.repository.AlwaysMatchesProductPriceRepository;
import com.acidtango.alexxispn.itxpricingbackend.pricing.products.infrastructure.repository.NoMatchProductPriceRepository;
import com.acidtango.alexxispn.itxpricingbackend.pricing.shared.domain.errors.ResourceNotFoundError;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class FindProductPriceFromDateTest {
    String productCode = "productCode";
    String brandCode = "brandCode";
    Instant date = Instant.parse("2020-06-14T00:00:00Z");
    ProductPrice dummyProductPrice = ProductPrice.create(
            new ProductPriceId(UUID.randomUUID().toString()),
            new ProductCode(productCode),
            new BrandCode(brandCode),
            new RangeDateTime(Instant.parse("2020-06-14T00:00:00Z"), Instant.parse("2020-12-31T23:59:59Z")),
            new Price(35.50, "EUR"),
            new Priority(0)
    );

    @Test
    public void not_find_a_product_price() {
        ProductPriceFromDateFinder finder = new ProductPriceFromDateFinder(
                new NoMatchProductPriceRepository()
        );

        assertThrows(ResourceNotFoundError.class, () ->
                finder.execute(brandCode, productCode, date)
        );
    }

    @Test
    public void find_a_product_price() {
        ProductPriceFromDateFinder finder = new ProductPriceFromDateFinder(
                new AlwaysMatchesProductPriceRepository(
                        new ProductPrice[]{
                                dummyProductPrice
                        }
                )
        );

        ProductPriceResponseDto productPrice = finder.execute(brandCode, productCode, date);

        assertEquals(productCode, productPrice.productCode());
    }

    @Test
    public void find_the_product_price_with_the_higher_priority() {
        Price higherPriorityPrice = new Price(35.50, "EUR");
        ProductPrice higherPriorityProductPrice = ProductPrice.create(
                new ProductPriceId(UUID.randomUUID().toString()),
                new ProductCode(productCode),
                new BrandCode(brandCode),
                new RangeDateTime(Instant.parse("2020-06-14T00:00:00Z"), Instant.parse("2020-12-31T23:59:59Z")),
                higherPriorityPrice,
                new Priority(1)
        );
        ProductPriceFromDateFinder finder = new ProductPriceFromDateFinder(
                new AlwaysMatchesProductPriceRepository(
                        new ProductPrice[]{
                                dummyProductPrice,
                                higherPriorityProductPrice
                        }
                )
        );

        ProductPriceResponseDto productPrice = finder.execute(brandCode, productCode, date);

        assertEquals(higherPriorityPrice.value(), productPrice.amount());
    }
}
