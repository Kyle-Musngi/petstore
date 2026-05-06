package com.musngi.petstore.pets.api;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record PetDetailResponse(
        UUID id,
        String name,
        String category,
        String description,
        String availabilityStatus,
        List<String> imageUrls,
        BigDecimal price) {
}
