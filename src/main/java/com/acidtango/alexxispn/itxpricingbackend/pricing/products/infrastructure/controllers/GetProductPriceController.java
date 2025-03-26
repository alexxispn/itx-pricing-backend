package com.acidtango.alexxispn.itxpricingbackend.pricing.products.infrastructure.controllers;

import com.acidtango.alexxispn.itxpricingbackend.pricing.products.application.ProductPriceFromDateFinder;
import com.acidtango.alexxispn.itxpricingbackend.pricing.products.infrastructure.controllers.dtos.ProductPriceResponseDto;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController
public class GetProductPriceController {

    private final ProductPriceFromDateFinder productPriceFromDateFinder;

    public GetProductPriceController(ProductPriceFromDateFinder productPriceFromDateFinder) {
        this.productPriceFromDateFinder = productPriceFromDateFinder;
    }

    @GetMapping("/brand/{brandCode}/product/{productCode}/price")
    public ProductPriceResponseDto getProductPrice(
            @PathVariable String brandCode,
            @PathVariable String productCode,
            @RequestParam Instant dateTime
    ) {
        return productPriceFromDateFinder.execute(brandCode, productCode, dateTime);
    }
}
