package com.musngi.petstore.pets.api;

import java.math.BigDecimal;
import java.util.UUID;

public record PetSummaryResponse(
        UUID id,
        String name,
        String category,
        String availabilityStatus,
        String imageUrl,
        BigDecimal price) {
}
