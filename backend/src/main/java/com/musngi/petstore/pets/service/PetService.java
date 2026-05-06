package com.musngi.petstore.pets.service;

import com.musngi.petstore.categories.model.PetCategory;
import com.musngi.petstore.categories.repository.PetCategoryRepository;
import com.musngi.petstore.common.api.errors.ResourceNotFoundException;
import com.musngi.petstore.pets.model.AvailabilityStatus;
import com.musngi.petstore.pets.model.Pet;
import com.musngi.petstore.pets.repository.PetRepository;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class PetService {
    private final PetRepository repository;
    private final PetCategoryRepository categoryRepository;

    public PetService(PetRepository repository, PetCategoryRepository categoryRepository) {
        this.repository = repository;
        this.categoryRepository = categoryRepository;
    }

    public List<Pet> listByCategory(String categorySlug) {
        return repository.findByCategory_NameAndCategory_IsActiveTrueOrderByListedAtDesc(categorySlug);
    }

    public Pet getById(UUID id) {
        return repository.findByIdAndCategory_IsActiveTrue(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pet not found"));
    }

    public Pet create(String name, UUID categoryId, String description, BigDecimal price, List<String> imageUrls) {
        PetCategory category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        Pet pet = new Pet();
        pet.setName(name);
        pet.setCategory(category);
        pet.setDescription(description);
        pet.setPrice(price);
        pet.setImageUrls(imageUrls != null ? imageUrls : List.of());
        pet.setAvailabilityStatus(AvailabilityStatus.AVAILABLE);

        return repository.save(pet);
    }

    public Pet update(UUID id, String name, String description, BigDecimal price, String availabilityStatus, List<String> imageUrls) {
        Pet pet = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pet not found"));

        if (name != null) {
            pet.setName(name);
        }
        if (description != null) {
            pet.setDescription(description);
        }
        if (price != null) {
            pet.setPrice(price);
        }
        if (availabilityStatus != null) {
            pet.setAvailabilityStatus(AvailabilityStatus.valueOf(availabilityStatus.toUpperCase()));
        }
        if (imageUrls != null) {
            pet.setImageUrls(imageUrls);
        }

        return repository.save(pet);
    }

    public void delete(UUID id) {
        Pet pet = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pet not found"));
        repository.delete(pet);
    }
}
