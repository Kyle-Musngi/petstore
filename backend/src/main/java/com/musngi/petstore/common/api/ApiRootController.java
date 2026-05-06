package com.musngi.petstore.common.api;

import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiRootController {
    @GetMapping("/")
    public ApiResponse<Map<String, String>> root() {
        return new ApiResponse<>(Map.of(
                "name", "PetStore API",
                "status", "ok"));
    }
}
