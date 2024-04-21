package com.acidtango.alexxispn.itxpricingbackend.pricing.products.domain;

import java.time.Instant;
import java.util.List;

public interface ProductPriceRepository {
    List<ProductPrice> find(String productCode, String brandCode, Instant date);
}



