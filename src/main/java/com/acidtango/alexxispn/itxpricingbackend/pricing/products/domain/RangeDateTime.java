package com.acidtango.alexxispn.itxpricingbackend.pricing.products.domain;

import java.time.LocalDateTime;

public class RangeDateTime {
    private final LocalDateTime fromDateTime;
    private final LocalDateTime toDateTime;

    public RangeDateTime(LocalDateTime fromDateTime, LocalDateTime toDateTime) {
        this.fromDateTime = fromDateTime;
        this.toDateTime = toDateTime;
    }

    public String fromDateTime() {
        return fromDateTime.toString();
    }

    public String toDateTime() {
        return toDateTime.toString();
    }

    public boolean contains(String date) {
        return fromDateTime.isBefore(LocalDateTime.parse(date)) && toDateTime.isAfter(LocalDateTime.parse(date)) || fromDateTime.isEqual(LocalDateTime.parse(date)) || toDateTime.isEqual(LocalDateTime.parse(date));
    }

    public boolean contains(LocalDateTime date) {
        return fromDateTime.isBefore(date) && toDateTime.isAfter(date) || fromDateTime.isEqual(date) || toDateTime.isEqual(date);
    }
}
