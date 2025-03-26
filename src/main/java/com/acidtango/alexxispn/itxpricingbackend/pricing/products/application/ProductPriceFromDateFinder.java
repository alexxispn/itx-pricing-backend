package com.acidtango.alexxispn.itxpricingbackend.pricing.products.application;

import com.acidtango.alexxispn.itxpricingbackend.pricing.products.domain.ProductPrice;
import com.acidtango.alexxispn.itxpricingbackend.pricing.products.domain.ProductPriceRepository;
import com.acidtango.alexxispn.itxpricingbackend.pricing.products.infrastructure.controllers.dtos.ProductPriceResponseDto;
import com.acidtango.alexxispn.itxpricingbackend.pricing.shared.application.UseCase;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class ProductPriceFromDateFinder extends UseCase {

    private final ProductPriceRepository repository;

    public ProductPriceFromDateFinder(ProductPriceRepository repository) {
        this.repository = repository;
    }

    public Optional<ProductPriceResponseDto> execute(String brandCode, String productCode, Instant dateTime) {
        List<ProductPrice> productPrices = repository.find(productCode, brandCode, dateTime);
        Optional<ProductPrice> productPriceWithHighestPriority =
                productPrices.stream().max(Comparator.comparingInt(ProductPrice::priority));
        return productPriceWithHighestPriority.map(productPrice -> new ProductPriceResponseDto(
                productPrice.toPrimitives().productCode(),
                productPrice.toPrimitives().brandCode(),
                productPrice.toPrimitives().fromDateTime(),
                productPrice.toPrimitives().toDateTime(),
                productPrice.toPrimitives().amount(),
                productPrice.toPrimitives().currencyCode()
        ));
    }
}
