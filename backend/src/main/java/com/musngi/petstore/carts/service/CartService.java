package com.musngi.petstore.carts.service;

import com.musngi.petstore.carts.model.Cart;
import com.musngi.petstore.carts.model.CartItem;
import com.musngi.petstore.carts.model.CartStatus;
import com.musngi.petstore.carts.repository.CartItemRepository;
import com.musngi.petstore.carts.repository.CartRepository;
import com.musngi.petstore.common.api.errors.PetUnavailableException;
import com.musngi.petstore.common.api.errors.ResourceNotFoundException;
import com.musngi.petstore.common.api.errors.ValidationException;
import com.musngi.petstore.pets.model.AvailabilityStatus;
import com.musngi.petstore.pets.model.Pet;
import com.musngi.petstore.pets.repository.PetRepository;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CartService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final PetRepository petRepository;

    public CartService(CartRepository cartRepository,
                       CartItemRepository cartItemRepository,
                       PetRepository petRepository) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.petRepository = petRepository;
    }

    @Transactional
    public Cart getOrCreateCart() {
        Optional<Cart> existing = cartRepository.findFirstByStatusOrderByCreatedAtDesc(CartStatus.OPEN);
        return existing.orElseGet(() -> cartRepository.save(new Cart()));
    }

    @Transactional
    public Cart addItem(UUID petId, int quantity) {
        if (quantity <= 0) {
            throw new ValidationException("Quantity must be greater than zero");
        }
        Pet pet = petRepository.findById(petId)
                .orElseThrow(() -> new ResourceNotFoundException("Pet not found"));
        if (pet.getAvailabilityStatus() != AvailabilityStatus.AVAILABLE) {
            throw new PetUnavailableException("Pet is no longer available");
        }
        Cart cart = getOrCreateCart();
        CartItem item = new CartItem();
        item.setCart(cart);
        item.setPet(pet);
        item.setQuantity(quantity);
        item.setPrice(pet.getPrice());
        cart.getItems().add(item);
        cartItemRepository.save(item);
        return cart;
    }

    @Transactional
    public Cart updateItem(UUID itemId, int quantity) {
        if (quantity <= 0) {
            throw new ValidationException("Quantity must be greater than zero");
        }
        CartItem item = cartItemRepository.findById(itemId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart item not found"));
        item.setQuantity(quantity);
        cartItemRepository.save(item);
        return item.getCart();
    }

    @Transactional
    public Cart removeItem(UUID itemId) {
        CartItem item = cartItemRepository.findById(itemId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart item not found"));
        Cart cart = item.getCart();
        cart.getItems().remove(item);
        cartItemRepository.delete(item);
        return cart;
    }

    public BigDecimal computeSubtotal(Cart cart) {
        return cart.getItems().stream()
                .map(item -> item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
