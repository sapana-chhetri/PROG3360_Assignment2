package com.example.order_service.Controller;

import com.example.order_service.Entity.Order;
import com.example.order_service.Service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
    @RequestMapping("/api")
    public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
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
        if(orderService.addOrder(order)) {
            return "Order placed successfully";
        }
        else{
            return "Order could not be placed";
        }
    }
    }


