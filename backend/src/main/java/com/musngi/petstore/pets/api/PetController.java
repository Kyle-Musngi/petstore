package com.musngi.petstore.pets.api;

import com.musngi.petstore.common.api.ApiResponse;
import com.musngi.petstore.pets.model.Pet;
import com.musngi.petstore.pets.service.PetService;
import java.util.List;
import java.util.UUID;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pets")
public class PetController {
    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @GetMapping
    public ApiResponse<List<PetSummaryResponse>> listPets(@RequestParam("category") String category) {
        List<PetSummaryResponse> results = petService.listByCategory(category).stream()
                .map(this::toSummary)
                .toList();
        return new ApiResponse<>(results);
    }

    @GetMapping("/{id}")
    public ApiResponse<PetDetailResponse> getPet(@PathVariable UUID id) {
        Pet pet = petService.getById(id);
        return new ApiResponse<>(toDetail(pet));
    }

    private PetSummaryResponse toSummary(Pet pet) {
        String imageUrl = pet.getImageUrls().isEmpty() ? null : pet.getImageUrls().get(0);
        return new PetSummaryResponse(
                pet.getId(),
                pet.getName(),
                pet.getCategory().getName(),
                pet.getAvailabilityStatus().name().toLowerCase(),
                imageUrl,
                pet.getPrice());
    }

    private PetDetailResponse toDetail(Pet pet) {
        return new PetDetailResponse(
                pet.getId(),
                pet.getName(),
                pet.getCategory().getName(),
                pet.getDescription(),
                pet.getAvailabilityStatus().name().toLowerCase(),
                pet.getImageUrls(),
                pet.getPrice());
    }
}
