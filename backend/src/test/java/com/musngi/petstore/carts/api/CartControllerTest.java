package com.musngi.petstore.carts.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.musngi.petstore.common.api.IntegrationTestBase;
import com.musngi.petstore.pets.repository.PetRepository;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CartControllerTest extends IntegrationTestBase {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PetRepository petRepository;

    @Test
    void addItemReturnsOk() throws Exception {
        UUID petId = petRepository.findAll().stream()
            .findFirst()
            .orElseThrow()
            .getId();
        CreateCartItemRequest request = new CreateCartItemRequest(petId, 1);
        mockMvc.perform(apiPost("/carts/items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isOk());
        }

        @Test
        void getCurrentCartReturnsOk() throws Exception {
        mockMvc.perform(apiPost("/carts/items")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(
                    new CreateCartItemRequest(
                        petRepository.findAll().stream().findFirst().orElseThrow().getId(),
                        1))))
            .andExpect(status().isOk());

        mockMvc.perform(apiGet("/carts/current"))
            .andExpect(status().isOk());
    }
}
