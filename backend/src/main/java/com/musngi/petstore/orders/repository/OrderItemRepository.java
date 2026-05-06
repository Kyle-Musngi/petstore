package com.musngi.petstore.orders.repository;

import com.musngi.petstore.orders.model.OrderItem;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, UUID> {
}
