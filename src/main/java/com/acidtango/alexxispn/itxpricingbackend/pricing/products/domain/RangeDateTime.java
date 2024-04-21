package com.acidtango.alexxispn.itxpricingbackend.pricing.products.domain;

import java.time.Instant;

public class RangeDateTime {
    private final Instant fromDateTime;
    private final Instant toDateTime;

    public RangeDateTime(Instant fromDateTime, Instant toDateTime) {
        this.fromDateTime = fromDateTime;
        this.toDateTime = toDateTime;
    }

    public Instant fromDateTime() {
        return fromDateTime;
    }

    public Instant toDateTime() {
        return toDateTime;
    }

    public boolean contains(Instant date) {
        return fromDateTime.compareTo(date) <= 0 && toDateTime.compareTo(date) >= 0;
    }
}
