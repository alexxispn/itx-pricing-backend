package com.acidtango.alexxispn.itxpricingbackend.pricing.products.application;

import com.acidtango.alexxispn.itxpricingbackend.pricing.products.domain.*;
import com.acidtango.alexxispn.itxpricingbackend.pricing.products.infrastructure.repository.InMemoryProductPriceReadModelRepository;
import com.acidtango.alexxispn.itxpricingbackend.pricing.products.infrastructure.repository.InMemoryProductPriceRepository;
import com.acidtango.alexxispn.itxpricingbackend.pricing.products.infrastructure.repository.ProductPriceReadModel;
import com.acidtango.alexxispn.itxpricingbackend.pricing.products.infrastructure.repository.ProductPriceReadModelRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FindProductPriceFromDateShould {

    private FindProductPriceFromDate findProductPriceFromDate;

    private ProductPriceRepository productPriceRepository;

    private ProductPriceReadModelRepository productPriceReadModelRepository;

    @BeforeEach
    public void setUp() {
        productPriceRepository = new InMemoryProductPriceRepository();
        productPriceReadModelRepository = new InMemoryProductPriceReadModelRepository(productPriceRepository);
        findProductPriceFromDate = new FindProductPriceFromDate(productPriceReadModelRepository);
    }

    @Test
    public void find_a_product_price() {
        String productCode = "productCode";
        String brandCode = "brandCode";
        LocalDateTime date = LocalDateTime.of(2020, 6, 14, 0, 0, 0);

        productPriceRepository.save(ProductPrice.create(
                new ProductPriceId(UUID.randomUUID().toString()),
                new ProductCode(productCode),
                new BrandCode(brandCode),
                new RangeDateTime(LocalDateTime.of(2020, 6, 14, 0, 0, 0), LocalDateTime.of(2020, 12, 31, 23, 59, 59)),
                new Price(35.50, "EUR"),
                new Priority(0)
        ));

        ProductPriceReadModel productPrice = findProductPriceFromDate.execute(brandCode, productCode, date);

        assertEquals(35.50, productPrice.amount());
    }
}
