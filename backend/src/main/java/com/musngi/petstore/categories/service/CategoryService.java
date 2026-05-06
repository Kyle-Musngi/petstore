package com.musngi.petstore.categories.service;

import com.musngi.petstore.categories.model.PetCategory;
import com.musngi.petstore.categories.repository.PetCategoryRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    private final PetCategoryRepository repository;

    public CategoryService(PetCategoryRepository repository) {
        this.repository = repository;
    }

    public List<PetCategory> listActive() {
        return repository.findByIsActiveTrueOrderByDisplayLabelAsc();
    }
}
