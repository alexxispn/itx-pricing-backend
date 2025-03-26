package com.acidtango.alexxispn.itxpricingbackend.pricing.products.infrastructure.controllers;

import com.acidtango.alexxispn.itxpricingbackend.pricing.products.application.ProductPriceFromDateFinder;
import com.acidtango.alexxispn.itxpricingbackend.pricing.products.infrastructure.controllers.dtos.ProductPriceResponseDto;
import com.acidtango.alexxispn.itxpricingbackend.pricing.shared.domain.errors.ResourceNotFoundError;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Optional;

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
        Optional<ProductPriceResponseDto> productPriceResponse = productPriceFromDateFinder.execute(brandCode, productCode, dateTime);
        if (productPriceResponse.isEmpty()) {
            throw new ResourceNotFoundError("No se encontró el precio para el producto " + productCode + " y marca " + brandCode +
                    " en la fecha especificada.");
        }
        return productPriceResponse.get();
    }
}

