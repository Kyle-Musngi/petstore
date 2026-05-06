package com.musngi.petstore.carts.api;

import java.math.BigDecimal;
import java.util.UUID;

public record CartItemResponse(UUID itemId, UUID petId, String name, int quantity, BigDecimal price) {
}
