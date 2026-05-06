package com.musngi.petstore.pets.repository;

import com.musngi.petstore.pets.model.Pet;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet, UUID> {
    List<Pet> findByCategory_NameAndCategory_IsActiveTrueOrderByListedAtDesc(String category);
    Optional<Pet> findByIdAndCategory_IsActiveTrue(UUID id);
}
