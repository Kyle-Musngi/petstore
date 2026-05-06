package com.musngi.petstore.pets.api;

import java.math.BigDecimal;
import java.util.List;

public record UpdatePetRequest(
        String name,
        String description,
        BigDecimal price,
        String availabilityStatus,
        List<String> imageUrls) {
}
