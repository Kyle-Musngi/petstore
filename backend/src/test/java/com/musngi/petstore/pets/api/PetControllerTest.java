package com.musngi.petstore.pets.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.musngi.petstore.categories.repository.PetCategoryRepository;
import com.musngi.petstore.common.api.IntegrationTestBase;
import com.musngi.petstore.pets.repository.PetRepository;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class PetControllerTest extends IntegrationTestBase {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private PetCategoryRepository categoryRepository;

    @Test
    void listPetsByCategory() throws Exception {
        mockMvc.perform(apiGet("/pets").param("category", "dogs"))
                .andExpect(status().isOk());
    }

    @Test
    void getPetById() throws Exception {
        UUID petId = petRepository.findAll().stream()
                .findFirst()
                .orElseThrow()
                .getId();

        mockMvc.perform(apiGet("/pets/{id}", petId))
                .andExpect(status().isOk());
    }

    @Test
    void createPetReturnsCreated() throws Exception {
        UUID categoryId = categoryRepository.findAll().stream()
                .findFirst()
                .orElseThrow()
                .getId();

        CreatePetRequest request = new CreatePetRequest(
                "Test Pet",
                categoryId,
                "A test pet",
                new BigDecimal("99.99"),
                List.of("https://example.com/image.jpg"));

        mockMvc.perform(apiPost("/pets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());
    }

    @Test
    void updatePetReturnsOk() throws Exception {
        UUID petId = petRepository.findAll().stream()
                .findFirst()
                .orElseThrow()
                .getId();

        UpdatePetRequest request = new UpdatePetRequest(
                "Updated Pet Name",
                "Updated description",
                new BigDecimal("149.99"),
                "unavailable",
                List.of("https://example.com/updated.jpg"));

        mockMvc.perform(apiPatch("/pets/{id}", petId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }

    @Test
    void deletePetReturnsNoContent() throws Exception {
        UUID petId = petRepository.findAll().stream()
                .findFirst()
                .orElseThrow()
                .getId();

        mockMvc.perform(apiDelete("/pets/{id}", petId))
                .andExpect(status().isNoContent());
    }
}
