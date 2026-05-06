package com.musngi.petstore.pets.service;

import com.musngi.petstore.common.api.errors.ResourceNotFoundException;
import com.musngi.petstore.pets.model.Pet;
import com.musngi.petstore.pets.repository.PetRepository;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class PetService {
    private final PetRepository repository;

    public PetService(PetRepository repository) {
        this.repository = repository;
    }

    public List<Pet> listByCategory(String categorySlug) {
        return repository.findByCategory_NameAndCategory_IsActiveTrueOrderByListedAtDesc(categorySlug);
    }

    public Pet getById(UUID id) {
        return repository.findByIdAndCategory_IsActiveTrue(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pet not found"));
    }
}
