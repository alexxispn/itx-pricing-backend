package com.acidtango.alexxispn.itxpricingbackend.pricing.shared.domain.errors;

public final class InvalidArgumentError extends DomainError {
    public InvalidArgumentError(String message) {
        super(message, DomainErrorCode.INVALID_ARGUMENT);
    }
}
