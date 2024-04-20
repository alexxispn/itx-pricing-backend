package com.acidtango.alexxispn.itxpricingbackend.pricing.products.domain;

public interface ProductPriceRepository {
    ProductPrice find(String productCode, String brandCode, String date);

    void save(ProductPrice productPrice);
}



