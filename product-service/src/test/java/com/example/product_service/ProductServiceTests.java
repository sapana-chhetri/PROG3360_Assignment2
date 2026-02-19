package com.example.product_service;

import com.example.product_service.Repository.Product;
import com.example.product_service.Service.FeatureFlagService;
import com.example.product_service.Service.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
public class ProductServiceTests {
    @Mock
    private FeatureFlagService featureFlagService;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private ProductService productService;

    // if flag enabled, apply discount to list of products. Else return regular list.
    @Test
    public void premiumPricingTest(){
        Product product = new Product();
        product.setPrice(100);
        product.setQuantity(10);

        productService.addProduct(product);
        var regularProducts = productService.getProducts();
        var discountedProducts = productService.getPremiumPricing();
        if(featureFlagService.isPremiumPricingEnabled()){
            assert (regularProducts.getFirst().getPrice()*.9 ==  discountedProducts.getFirst().getPrice());
        } else {
            assert (regularProducts.getFirst().getPrice() ==  discountedProducts.getFirst().getPrice());
        }

    }

//    @Test
//    public void premiumPricingDisabledTest(){
//        Product product = new Product();
//        product.setPrice(100);
//        product.setQuantity(10);
//
//        productService.addProduct(product);
//        var regularProducts = productService.getProducts();
//        var discountedProducts = productService.getPremiumPricing();
//        assert (regularProducts.getFirst().getPrice() ==  discountedProducts.getFirst().getPrice());
//    }
}
