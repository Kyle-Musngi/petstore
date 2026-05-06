package com.musngi.petstore.orders.api;

import java.math.BigDecimal;
import java.util.UUID;

public record OrderResponse(UUID orderId, String status, BigDecimal total) {
}
