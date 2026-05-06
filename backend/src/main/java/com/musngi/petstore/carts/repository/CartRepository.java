package com.musngi.petstore.carts.repository;

import com.musngi.petstore.carts.model.Cart;
import com.musngi.petstore.carts.model.CartStatus;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, UUID> {
    Optional<Cart> findFirstByStatusOrderByCreatedAtDesc(CartStatus status);
}
