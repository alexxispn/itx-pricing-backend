package com.acidtango.alexxispn.itxpricingbackend.pricing.products.infrastructure.repository;

import com.acidtango.alexxispn.itxpricingbackend.pricing.products.domain.ProductPrice;
import com.acidtango.alexxispn.itxpricingbackend.pricing.products.domain.ProductPriceRepository;
import org.springframework.stereotype.Component;

@Component
public class InMemoryProductPriceReadModelRepository implements ProductPriceReadModelRepository {
    ProductPriceRepository productPriceRepository;

    public InMemoryProductPriceReadModelRepository(ProductPriceRepository productPriceRepository) {
        this.productPriceRepository = productPriceRepository;
    }

    @Override
    public ProductPriceReadModel find(String productCode, String brandCode, String date) {
        ProductPrice productPrice = productPriceRepository.find(productCode, brandCode, date);
        if (productPrice == null) {
            return null;
        }
        return new ProductPriceReadModel(
                productPrice.toPrimitives().id(),
                productPrice.toPrimitives().productCode(),
                productPrice.toPrimitives().brandCode(),
                productPrice.toPrimitives().fromDateTime(),
                productPrice.toPrimitives().toDateTime(),
                productPrice.toPrimitives().amount(),
                productPrice.toPrimitives().currencyCode()
        );
    }
}

