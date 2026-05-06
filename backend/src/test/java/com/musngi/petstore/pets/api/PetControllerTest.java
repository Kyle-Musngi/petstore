package com.musngi.petstore.pets.api;

import com.musngi.petstore.common.api.IntegrationTestBase;
import com.musngi.petstore.pets.repository.PetRepository;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class PetControllerTest extends IntegrationTestBase {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PetRepository petRepository;

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
}
