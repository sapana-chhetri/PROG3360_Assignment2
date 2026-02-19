package com.example.order_service;

import com.example.order_service.Entity.Order;
import com.example.order_service.Entity.Product;
import com.example.order_service.Service.FeatureFlagService;
import com.example.order_service.Service.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    private FeatureFlagService featureFlagService;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private OrderService orderService;

    //Test Feature Flag(3) states
    //Feature Flag bulk-order-discount on and order quantity exceeds 5 items
    @Test
    public void bulkOrderDiscountFlagOnQuantityGreaterThanFive(){

       Order order = new Order();
       //Ordered amount
       order.setQuantity(7);

       Product product = new Product();
       //Item price
       product.setPrice(150.00);
       //Quantity in stock
       product.setQuantity(10);

       when(restTemplate.getForObject(anyString(), eq(Product.class))).thenReturn(product);
       when(featureFlagService.isBulkOrderDiscountEnabled()).thenReturn(true);

       orderService.addOrder(order);

       //15% discount
       assertEquals(150.00 * 7 *0.85, order.getTotalPrice());
    }

    //Feature Flag bulk-order-discount off
    @Test
    public void bulkOrderDiscountFlagOffNoDiscount(){

        Order order = new Order();
        //Ordered amount
        order.setQuantity(6);

        Product product = new Product();
        //Item price
        product.setPrice(110.00);
        //Quantity in stock
        product.setQuantity(10);

        when(restTemplate.getForObject(anyString(), eq(Product.class))).thenReturn(product);
        when(featureFlagService.isBulkOrderDiscountEnabled()).thenReturn(false);

        orderService.addOrder(order);

        //NO 15% discount
        assertEquals(110.00 * 6, order.getTotalPrice());
    }
    //Feature Flag bulk-order-discount on and order quantity is less than or equal to 5 items
    @Test
    public void bulkOrderDiscountFlagOnQuantityLessThanFiveNoDiscount(){

        Order order = new Order();
        //Ordered amount
        order.setQuantity(4);

        Product product = new Product();
        //Item price
        product.setPrice(110.00);
        //Quantity in stock
        product.setQuantity(10);

        when(restTemplate.getForObject(anyString(), eq(Product.class))).thenReturn(product);
        when(featureFlagService.isBulkOrderDiscountEnabled()).thenReturn(true);

        orderService.addOrder(order);

        //NO 15% discount
        assertEquals(110.00 * 4, order.getTotalPrice());
    }
    //Feature Flag Order Notification enabled
    @Test
    public void testAddOrder_SuccessWithNotificationEnabled(){
        //order details
        Order order=new Order(1,3,"NEW",0);
        //product details to ensure that the order works
        Product product=new Product();
        product.setQuantity(10);


        when(restTemplate.getForObject(anyString(), eq(Product.class))).thenReturn(product);
        when(featureFlagService.isOrderNotificationsEnabled()).thenReturn(true);

        ByteArrayOutputStream outContent= new ByteArrayOutputStream();
        System.setOut((new PrintStream(outContent)));

        orderService.addOrder(order);

        //log
        String expectedOutput= "Order Confirmation"+
                "\nOrder ID"+order.getId()+
                "\nProduct ID:" + order.getProductId() +
                "\nQuantity:" + order.getQuantity() +
                "\nTotal Price";
        assertEquals(expectedOutput.trim(),outContent.toString().trim());

    }
    //Feature Flag Order Notification disabled
    @Test
    public void testAddOrder_SuccessWithNotificationDisabled(){
        //order details
        Order order=new Order(1,3,"NEW",0);
        //product details to ensure that the order works
        Product product=new Product();
        product.setQuantity(10);

        when(restTemplate.getForObject(anyString(), eq(Product.class))).thenReturn(product);
        when(featureFlagService.isOrderNotificationsEnabled()).thenReturn(false);

        ByteArrayOutputStream outContent= new ByteArrayOutputStream();
        System.setOut((new PrintStream(outContent)));

        orderService.addOrder(order);
        //no log
        assertEquals("",outContent.toString().trim());

    }
}
