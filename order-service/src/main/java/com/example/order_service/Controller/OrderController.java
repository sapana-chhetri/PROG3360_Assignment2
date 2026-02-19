package com.example.order_service.Controller;

import com.example.order_service.Entity.Order;
import com.example.order_service.Service.FeatureFlagService;
import com.example.order_service.Service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;
import java.util.Optional;

@RestController
    @RequestMapping("/api")
    public class OrderController {

    private final FeatureFlagService featureFlagService;
    private final OrderService orderService;

    public OrderController(FeatureFlagService featureFlagService, OrderService orderService) {
        this.featureFlagService = featureFlagService;
        this.orderService = orderService;
    }

    @GetMapping("/orders")
    public List<Order> getAllOrders() {
        return orderService.getOrders();
    }

    @GetMapping("/orders/{id}")
    public Optional<Order> getOrderById(@PathVariable int id) {
        return orderService.getOrderById(id);
    }

    @PostMapping("/orders")
    public String addOrder( @RequestBody Order order) {

        //Calculate total order price
        double totalPrice = order.getTotalPrice() * order.getQuantity();

        //If feature flag 3 is ON and more than 5 items add 15% discount
        if(featureFlagService.isBulkOrderDiscountEnabled() && order.getQuantity() > 5){
            totalPrice = totalPrice - (totalPrice * 0.15);
        }

        //If bulk-order-discount flag is OFF: Calculate totalPrice regardless of quantity
        order.setTotalPrice(totalPrice);

        //Save / Place order
        if(orderService.addOrder(order)) {
            return "Order placed successfully";
        }
        else{
            return "Order could not be placed";
        }
    }
    }


