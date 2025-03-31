package com.acidtango.alexxispn.itxpricingbackend.pricing.products.application;

import com.acidtango.alexxispn.itxpricingbackend.pricing.products.domain.ProductPrice;
import com.acidtango.alexxispn.itxpricingbackend.pricing.products.domain.ProductPriceRepository;
import com.acidtango.alexxispn.itxpricingbackend.pricing.products.infrastructure.controllers.dtos.ProductPriceResponseDto;
import com.acidtango.alexxispn.itxpricingbackend.pricing.shared.domain.errors.ProductPriceNotFoundError;
import com.acidtango.alexxispn.itxpricingbackend.pricing.shared.application.UseCase;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Comparator;
import java.util.List;

@Service
public class ProductPriceFromDateFinder extends UseCase {

    private final ProductPriceRepository repository;

    public ProductPriceFromDateFinder(ProductPriceRepository repository) {
        this.repository = repository;
    }

    public ProductPriceResponseDto execute(String brandCode, String productCode, Instant dateTime) {
        List<ProductPrice> productPrices = repository.find(productCode, brandCode, dateTime);
        ProductPrice productPrice = productPrices.stream()
                .max(Comparator.comparingInt(ProductPrice::priority))
                .orElseThrow(() -> new ProductPriceNotFoundError(productCode, brandCode));
        return mapToDto(productPrice);
    }

    private ProductPriceResponseDto mapToDto(ProductPrice productPrice) {
        var primitives = productPrice.toPrimitives();
        return new ProductPriceResponseDto(
                primitives.productCode(),
                primitives.brandCode(),
                primitives.fromDateTime(),
                primitives.toDateTime(),
                primitives.amount(),
                primitives.currencyCode()
        );
    }
}

