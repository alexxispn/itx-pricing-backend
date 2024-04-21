package com.acidtango.alexxispn.itxpricingbackend.pricing.products.application;

import com.acidtango.alexxispn.itxpricingbackend.pricing.products.infrastructure.repository.ProductPriceReadModel;
import com.acidtango.alexxispn.itxpricingbackend.pricing.products.infrastructure.repository.ProductPriceReadModelRepository;
import com.acidtango.alexxispn.itxpricingbackend.pricing.shared.application.UseCase;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class FindProductPriceFromDate extends UseCase {

    private final ProductPriceReadModelRepository repository;

    public FindProductPriceFromDate(ProductPriceReadModelRepository repository) {
        this.repository = repository;
    }

    public ProductPriceReadModel execute(String brandCode, String productCode, LocalDateTime dateTime) {
        return repository.find(productCode, brandCode, dateTime.toString());
    }
}
