package com.musngi.petstore.carts.api;

import com.musngi.petstore.carts.model.Cart;
import com.musngi.petstore.carts.model.CartItem;
import com.musngi.petstore.carts.service.CartService;
import com.musngi.petstore.common.api.ApiResponse;
import java.util.List;
import java.util.UUID;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/carts")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/items")
    public ApiResponse<CartResponse> addItem(@RequestBody CreateCartItemRequest request) {
        Cart cart = cartService.addItem(request.petId(), request.quantity());
        return new ApiResponse<>(toResponse(cart));
    }

    @GetMapping("/current")
    public ApiResponse<CartResponse> getCurrent() {
        Cart cart = cartService.getOrCreateCart();
        return new ApiResponse<>(toResponse(cart));
    }

    @PatchMapping("/items/{itemId}")
    public ApiResponse<CartResponse> updateItem(
            @PathVariable UUID itemId,
            @RequestBody UpdateCartItemRequest request) {
        Cart cart = cartService.updateItem(itemId, request.quantity());
        return new ApiResponse<>(toResponse(cart));
    }

    @DeleteMapping("/items/{itemId}")
    public ApiResponse<CartResponse> removeItem(@PathVariable UUID itemId) {
        Cart cart = cartService.removeItem(itemId);
        return new ApiResponse<>(toResponse(cart));
    }

    private CartResponse toResponse(Cart cart) {
        List<CartItemResponse> items = cart.getItems().stream()
                .map(this::toItemResponse)
                .toList();
        return new CartResponse(cart.getId(), items, cartService.computeSubtotal(cart));
    }

    private CartItemResponse toItemResponse(CartItem item) {
        return new CartItemResponse(
                item.getId(),
                item.getPet().getId(),
                item.getPet().getName(),
                item.getQuantity(),
                item.getPrice());
    }
}
