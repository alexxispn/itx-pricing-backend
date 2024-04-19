package com.acidtango.alexxispn.itxpricingbackend.pricing.products.domain;

public class Amount {
    private final Double value;

    public Amount(Double value) {
        this.value = value;
    }

    public Double value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Amount that = (Amount) o;
        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
