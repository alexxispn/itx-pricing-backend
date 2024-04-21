package com.acidtango.alexxispn.itxpricingbackend.pricing.products.infrastructure.controllers;

import com.acidtango.alexxispn.itxpricingbackend.pricing.products.application.FindProductPriceFromDate;
import com.acidtango.alexxispn.itxpricingbackend.pricing.products.infrastructure.controllers.dtos.ProductPriceResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Optional;

@RestController
public class GetProductPriceController {
    private final FindProductPriceFromDate findProductPriceFromDate;

    public GetProductPriceController(FindProductPriceFromDate findProductPriceFromDate) {
        this.findProductPriceFromDate = findProductPriceFromDate;
    }

    @GetMapping("/brand/{brandCode}/product/{productCode}/price")
    public ProductPriceResponseDto getProductPrice(
            @PathVariable String brandCode,
            @PathVariable String productCode,
            @RequestParam Instant dateTime
    ) {
        Optional<ProductPriceResponseDto> productPriceResponse = findProductPriceFromDate.execute(brandCode, productCode,
                dateTime);
        return productPriceResponse.get();
    }
}

