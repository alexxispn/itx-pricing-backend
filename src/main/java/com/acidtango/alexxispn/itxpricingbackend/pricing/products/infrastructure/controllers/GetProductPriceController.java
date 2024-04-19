package com.acidtango.alexxispn.itxpricingbackend.pricing.products.infrastructure.controllers;

import com.acidtango.alexxispn.itxpricingbackend.pricing.products.application.FindProductPriceFromDate;
import com.acidtango.alexxispn.itxpricingbackend.pricing.products.infrastructure.controllers.dtos.GetProductPriceResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class GetProductPriceController {
    @Autowired
    private FindProductPriceFromDate findProductPriceFromDate;

    @GetMapping("/brand/{brandCode}/product/{productCode}/price")
    public GetProductPriceResponseDto getProductPrice(
            @PathVariable String brandCode,
            @PathVariable String productCode,
            @RequestParam LocalDateTime dateTime
    ) {
        return GetProductPriceResponseDto.fromDomain(
                findProductPriceFromDate.execute(brandCode, productCode, dateTime)
        );
    }
}

