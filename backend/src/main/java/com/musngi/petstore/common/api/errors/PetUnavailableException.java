package com.musngi.petstore.common.api.errors;

public class PetUnavailableException extends RuntimeException {
    public PetUnavailableException(String message) {
        super(message);
    }
}
