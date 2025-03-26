package com.acidtango.alexxispn.itxpricingbackend.pricing.shared.domain.errors;

public sealed abstract class DomainError extends RuntimeException
        permits ResourceNotFoundError {

    private final DomainErrorCode code;

    protected DomainError(String message, DomainErrorCode code) {
        super(message);
        this.code = code;
    }

    public DomainErrorCode getCode() {
        return code;
    }
}

