package com.acidtango.alexxispn.itxpricingbackend.pricing.products.infrastructure.controllers;

import com.acidtango.alexxispn.itxpricingbackend.pricing.products.application.FindProductPriceFromDate;
import com.acidtango.alexxispn.itxpricingbackend.pricing.products.infrastructure.controllers.dtos.GetProductPriceResponseDto;
import com.acidtango.alexxispn.itxpricingbackend.pricing.products.infrastructure.controllers.dtos.ProductPriceResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Optional;

@RestController
public class GetProductPriceController {
    private FindProductPriceFromDate findProductPriceFromDate;

    public GetProductPriceController(FindProductPriceFromDate findProductPriceFromDate) {
        this.findProductPriceFromDate = findProductPriceFromDate;
    }

    @GetMapping("/brand/{brandCode}/product/{productCode}/price")
    public GetProductPriceResponseDto getProductPrice(
            @PathVariable String brandCode,
            @PathVariable String productCode,
            @RequestParam Instant dateTime
    ) {
        Optional<ProductPriceResponseDto> productPriceReadModel = findProductPriceFromDate.execute(brandCode, productCode,
                dateTime);
        return new GetProductPriceResponseDto(
                productPriceReadModel.get().productCode(),
                productPriceReadModel.get().brandCode(),
                productPriceReadModel.get().fromDateTime(),
                productPriceReadModel.get().toDateTime(),
                productPriceReadModel.get().amount(),
                productPriceReadModel.get().currencyCode()
        );
    }
}

