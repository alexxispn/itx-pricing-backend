package com.acidtango.alexxispn.itxpricingbackend.pricing.products.application;

import com.acidtango.alexxispn.itxpricingbackend.pricing.products.domain.*;
import com.acidtango.alexxispn.itxpricingbackend.pricing.products.infrastructure.controllers.dtos.ProductPriceResponseDto;
import com.acidtango.alexxispn.itxpricingbackend.pricing.products.infrastructure.repository.InMemoryProductPriceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FindProductPriceFromDateShould {
    private FindProductPriceFromDate findProductPriceFromDate;

    private InMemoryProductPriceRepository productPriceRepository;


    @BeforeEach
    public void setUp() {
        productPriceRepository = new InMemoryProductPriceRepository();
        findProductPriceFromDate = new FindProductPriceFromDate(productPriceRepository);
    }

    @Test
    public void find_a_product_price() {
        String productCode = "productCode";
        String brandCode = "brandCode";
        Instant date = Instant.parse("2020-06-14T00:00:00Z");

        productPriceRepository.save(ProductPrice.create(
                new ProductPriceId(UUID.randomUUID().toString()),
                new ProductCode(productCode),
                new BrandCode(brandCode),
                new RangeDateTime(Instant.parse("2020-06-14T00:00:00Z"), Instant.parse("2020-12-31T23:59:59Z")),
                new Price(35.50, "EUR"),
                new Priority(0)
        ));

        Optional<ProductPriceResponseDto> productPrice = findProductPriceFromDate.execute(brandCode, productCode, date);

        assertEquals(35.50, productPrice.get().amount());
    }

    @Test
    public void not_find_a_product_price() {
        String productCode = "productCode";
        String brandCode = "brandCode";
        Instant date = Instant.parse("2020-06-14T00:00:00Z");

        Optional<ProductPriceResponseDto> productPrice = findProductPriceFromDate.execute(brandCode, productCode, date);

        assertEquals(Optional.empty(), productPrice);
    }

    @Test
    public void find_product_price_with_the_higher_priority() {
        String productCode = "productCode";
        String brandCode = "brandCode";
        Instant date = Instant.parse("2020-06-14T00:00:00Z");

        productPriceRepository.save(ProductPrice.create(
                new ProductPriceId(UUID.randomUUID().toString()),
                new ProductCode(productCode),
                new BrandCode(brandCode),
                new RangeDateTime(Instant.parse("2020-06-14T00:00:00Z"), Instant.parse("2020-12-31T23:59:59Z")),
                new Price(35.50, "EUR"),
                new Priority(0)
        ));

        productPriceRepository.save(ProductPrice.create(
                new ProductPriceId(UUID.randomUUID().toString()),
                new ProductCode(productCode),
                new BrandCode(brandCode),
                new RangeDateTime(Instant.parse("2020-06-14T00:00:00Z"), Instant.parse("2020-12-31T23:59:59Z")),
                new Price(25.45, "EUR"),
                new Priority(1)
        ));

        Optional<ProductPriceResponseDto> productPrice = findProductPriceFromDate.execute(brandCode, productCode, date);

        assertEquals(25.45, productPrice.get().amount());
    }
}
