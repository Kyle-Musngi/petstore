package com.musngi.petstore.orders.api;

import com.musngi.petstore.common.api.ApiResponse;
import com.musngi.petstore.common.api.errors.ValidationException;
import com.musngi.petstore.orders.model.Order;
import com.musngi.petstore.orders.service.OrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ApiResponse<OrderResponse> createOrder(@RequestBody CreateOrderRequest request) {
        if (request == null || request.delivery() == null) {
            throw new ValidationException("Delivery details are required");
        }
        Order order = orderService.createOrder(
                request.delivery().fullName(),
                request.delivery().email(),
                request.delivery().addressLine1(),
                request.delivery().addressLine2(),
                request.delivery().city(),
                request.delivery().state(),
                request.delivery().postalCode());
        return new ApiResponse<>(new OrderResponse(order.getId(), order.getStatus().name().toLowerCase(), order.getTotal()));
    }
}
