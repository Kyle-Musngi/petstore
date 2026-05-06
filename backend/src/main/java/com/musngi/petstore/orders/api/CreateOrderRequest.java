package com.musngi.petstore.orders.api;

public record CreateOrderRequest(Delivery delivery) {
    public record Delivery(
            String fullName,
            String email,
            String addressLine1,
            String addressLine2,
            String city,
            String state,
            String postalCode) {
    }
}
