package com.musngi.petstore.common.api;

import com.musngi.petstore.common.api.errors.PetUnavailableException;
import com.musngi.petstore.common.api.errors.ResourceNotFoundException;
import com.musngi.petstore.common.api.errors.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(RestExceptionHandler.class);

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleNotFound(ResourceNotFoundException ex) {
        LOGGER.warn("Resource not found: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiErrorResponse(new ApiError("not_found", ex.getMessage(), requestId())));
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ApiErrorResponse> handleValidation(ValidationException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ApiErrorResponse(new ApiError("validation_error", ex.getMessage(), requestId())));
    }

    @ExceptionHandler(PetUnavailableException.class)
    public ResponseEntity<ApiErrorResponse> handleUnavailable(PetUnavailableException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ApiErrorResponse(new ApiError("pet_unavailable", ex.getMessage(), requestId())));
    }

    private String requestId() {
        String requestId = MDC.get(RequestIdFilter.HEADER_NAME);
        return requestId == null ? "" : requestId;
    }
}
