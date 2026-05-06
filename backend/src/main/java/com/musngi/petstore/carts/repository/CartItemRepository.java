package com.musngi.petstore.carts.repository;

import com.musngi.petstore.carts.model.CartItem;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, UUID> {
}
