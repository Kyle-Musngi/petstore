package com.musngi.petstore.orders.repository;

import com.musngi.petstore.orders.model.Order;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, UUID> {
}
