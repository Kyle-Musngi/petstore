package com.musngi.petstore.pets.api;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record CreatePetRequest(
        String name,
        UUID categoryId,
        String description,
        BigDecimal price,
        List<String> imageUrls) {
}
