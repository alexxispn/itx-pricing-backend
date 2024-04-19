package com.acidtango.alexxispn.itxpricingbackend.pricing.products.domain;

public class Price {
    private final double value;
    private final String currency;

    public Price(double value, String currency) {
        this.value = value;
        this.currency = currency;
    }

    public double value() {
        return value;
    }

    public String currency() {
        return currency;
    }
}
