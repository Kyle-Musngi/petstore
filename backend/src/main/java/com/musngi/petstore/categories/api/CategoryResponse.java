package com.musngi.petstore.categories.api;

import java.util.UUID;

public record CategoryResponse(UUID id, String name, String displayLabel, boolean isActive) {
}
