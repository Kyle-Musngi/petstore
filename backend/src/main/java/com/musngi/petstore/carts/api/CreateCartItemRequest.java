package com.musngi.petstore.carts.api;

import java.util.UUID;

public record CreateCartItemRequest(UUID petId, int quantity) {
}
