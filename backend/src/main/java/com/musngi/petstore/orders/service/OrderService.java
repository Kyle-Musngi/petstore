package com.musngi.petstore.orders.service;

import com.musngi.petstore.carts.model.Cart;
import com.musngi.petstore.carts.model.CartItem;
import com.musngi.petstore.carts.model.CartStatus;
import com.musngi.petstore.carts.repository.CartRepository;
import com.musngi.petstore.common.api.errors.PetUnavailableException;
import com.musngi.petstore.common.api.errors.ResourceNotFoundException;
import com.musngi.petstore.common.api.errors.ValidationException;
import com.musngi.petstore.orders.model.Order;
import com.musngi.petstore.orders.model.OrderItem;
import com.musngi.petstore.orders.repository.OrderRepository;
import com.musngi.petstore.pets.model.AvailabilityStatus;
import java.math.BigDecimal;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {
    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;

    public OrderService(CartRepository cartRepository, OrderRepository orderRepository) {
        this.cartRepository = cartRepository;
        this.orderRepository = orderRepository;
    }

    @Transactional
    public Order createOrder(String fullName,
                             String email,
                             String addressLine1,
                             String addressLine2,
                             String city,
                             String state,
                             String postalCode) {
        if (isBlank(fullName) || isBlank(email) || isBlank(addressLine1)
                || isBlank(city) || isBlank(state) || isBlank(postalCode)) {
            throw new ValidationException("Delivery details are required");
        }
        Cart cart = cartRepository.findFirstByStatusOrderByCreatedAtDesc(CartStatus.OPEN)
            .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));
        if (cart.getItems().isEmpty()) {
            throw new ValidationException("Cart is empty");
        }
        for (CartItem item : cart.getItems()) {
            if (item.getPet().getAvailabilityStatus() != AvailabilityStatus.AVAILABLE) {
                throw new PetUnavailableException("Pet is no longer available");
            }
        }

        Order order = new Order();
        order.setCart(cart);
        order.setDeliveryFullName(fullName);
        order.setDeliveryEmail(email);
        order.setDeliveryAddressLine1(addressLine1);
        order.setDeliveryAddressLine2(addressLine2);
        order.setDeliveryCity(city);
        order.setDeliveryState(state);
        order.setDeliveryPostalCode(postalCode);

        BigDecimal total = BigDecimal.ZERO;
        for (CartItem item : cart.getItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setPet(item.getPet());
            orderItem.setQuantity(item.getQuantity());
            orderItem.setPrice(item.getPrice());
            order.getItems().add(orderItem);
            total = total.add(item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
        }
        order.setTotal(total);

        cart.setStatus(CartStatus.CHECKED_OUT);
        cartRepository.save(cart);

        return orderRepository.save(order);
    }

    private boolean isBlank(String value) {
        return value == null || value.isBlank();
    }
}
