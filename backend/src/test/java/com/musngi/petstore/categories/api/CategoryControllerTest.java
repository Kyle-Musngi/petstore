package com.musngi.petstore.categories.api;

import com.musngi.petstore.common.api.IntegrationTestBase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CategoryControllerTest extends IntegrationTestBase {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void listCategories() throws Exception {
        mockMvc.perform(apiGet("/categories"))
                .andExpect(status().isOk());
    }
}
