package com.musngi.petstore.categories.repository;

import com.musngi.petstore.categories.model.PetCategory;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetCategoryRepository extends JpaRepository<PetCategory, UUID> {
    List<PetCategory> findByIsActiveTrueOrderByDisplayLabelAsc();
}
