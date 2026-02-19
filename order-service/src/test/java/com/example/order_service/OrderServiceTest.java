package com.example.order_service;

import com.example.order_service.Service.FeatureFlagService;
import com.example.order_service.Service.OrderService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class OrderServiceTest {

    @Mock
    private FeatureFlagService featureFlagService;

    @InjectMocks
    private OrderService orderService;

    //Test Feature Flag(3) states
    //Feature Flag bulk-order-discount on and order quantity exceeds 5 items
    //Feature Flag bulk-order-discount off
    //Feature Flag bulk-order-discount on and order quantity is less than or equal to 5 items
}
