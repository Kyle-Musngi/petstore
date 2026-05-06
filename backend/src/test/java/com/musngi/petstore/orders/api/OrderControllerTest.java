package com.musngi.petstore.orders.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.musngi.petstore.common.api.IntegrationTestBase;
import com.musngi.petstore.carts.service.CartService;
import com.musngi.petstore.pets.repository.PetRepository;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class OrderControllerTest extends IntegrationTestBase {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private CartService cartService;

    @Test
    void checkoutRequiresDelivery() throws Exception {
        CreateOrderRequest request = new CreateOrderRequest(null);
        mockMvc.perform(apiPost("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

        @Test
        void createOrderReturnsOk() throws Exception {
        UUID petId = petRepository.findAll().stream()
            .findFirst()
            .orElseThrow()
            .getId();
        cartService.addItem(petId, 1);

        CreateOrderRequest.Delivery delivery = new CreateOrderRequest.Delivery(
            "Sam Jones",
            "sam@example.com",
            "123 Main St",
            "Apt 4",
            "Portland",
            "OR",
            "97201");
        CreateOrderRequest request = new CreateOrderRequest(delivery);
        mockMvc.perform(apiPost("/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isOk());
        }
}
