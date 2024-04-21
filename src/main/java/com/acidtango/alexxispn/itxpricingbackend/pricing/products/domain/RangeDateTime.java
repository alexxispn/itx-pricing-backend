package com.acidtango.alexxispn.itxpricingbackend.pricing.products.domain;

import java.time.Instant;

public record RangeDateTime(Instant fromDateTime, Instant toDateTime) {

    public boolean contains(Instant date) {
        return fromDateTime.compareTo(date) <= 0 && toDateTime.compareTo(date) >= 0;
    }
}
