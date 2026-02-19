package com.example.order_service.Service;

import com.example.order_service.Entity.Order;
import com.example.order_service.Entity.Product;
import com.example.order_service.Repository.OrderRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final RestTemplate restTemplate = new RestTemplate();
    private String productUrl = "http://localhost:8081/api/products";

    private final FeatureFlagService featureFlagService;
    private OrderRepository orderRepository;

    public OrderService(FeatureFlagService featureFlagService, OrderRepository orderRepository) {
        this.featureFlagService = featureFlagService;
        this.orderRepository = orderRepository;
    }

    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(int id) {
        return orderRepository.findById(id);
    }

    public boolean addOrder(Order order) {
        // if (product service get by id) quantity >= order quantity
        // return true
        // else return false
        Product product
                = restTemplate.getForObject(productUrl + "/" + order.getProductId(), Product.class);
        if(product==null || product.getQuantity() < order.getQuantity()) {
            return false;
        }

        //Calculate total order price
        double totalPrice = product.getPrice() * order.getQuantity();

        //If feature flag 3 is ON and more than 5 items add 15% discount
        if(featureFlagService.isBulkOrderDiscountEnabled() && order.getQuantity() > 5){
            totalPrice = totalPrice - (totalPrice * 0.15);
        }

        //If bulk-order-discount flag is OFF: Calculate totalPrice regardless of quantity
        order.setTotalPrice(totalPrice);

        orderRepository.save(order);
        return true;
    }
}



