package com.musngi.petstore.common.api;

public record ApiError(String code, String message, String requestId) {
}
