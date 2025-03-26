package com.acidtango.alexxispn.itxpricingbackend.pricing.shared.domain.errors;

public final class ResourceNotFoundError extends DomainError {
    public ResourceNotFoundError(String message) {
        super(message, DomainErrorCode.NOT_FOUND);
    }
}

