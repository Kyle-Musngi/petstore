package com.musngi.petstore.categories.api;

import com.musngi.petstore.categories.model.PetCategory;
import com.musngi.petstore.categories.service.CategoryService;
import com.musngi.petstore.common.api.ApiResponse;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ApiResponse<List<CategoryResponse>> listCategories() {
        List<CategoryResponse> results = categoryService.listActive().stream()
                .map(this::toResponse)
                .toList();
        return new ApiResponse<>(results);
    }

    private CategoryResponse toResponse(PetCategory category) {
        return new CategoryResponse(
                category.getId(),
                category.getName(),
                category.getDisplayLabel(),
                category.isActive());
    }
}
