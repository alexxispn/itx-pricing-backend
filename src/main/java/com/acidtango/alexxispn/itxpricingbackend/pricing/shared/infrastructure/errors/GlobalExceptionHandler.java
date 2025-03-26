package com.acidtango.alexxispn.itxpricingbackend.pricing.shared.infrastructure.errors;

import com.acidtango.alexxispn.itxpricingbackend.pricing.shared.domain.errors.DomainError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorResponse> handleMissingParameter(MissingServletRequestParameterException ex) {
        String errorMessage = "El parámetro '" + ex.getParameterName() + "' es obligatorio.";
        ErrorResponse errorResponse = new ErrorResponse("BAD_REQUEST", errorMessage);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DomainError.class)
    public ResponseEntity<ErrorResponse> handleDomainError(DomainError ex) {
        HttpStatus status;
        switch (ex.getCode()) {
            case NOT_FOUND -> status = HttpStatus.NOT_FOUND;
            case INVALID_ARGUMENT -> status = HttpStatus.BAD_REQUEST;
            default -> status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        ErrorResponse errorResponse = new ErrorResponse(ex.getCode().name(), ex.getMessage());
        return new ResponseEntity<>(errorResponse, status);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneralException(Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse("UNKNOWN_ERROR", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
