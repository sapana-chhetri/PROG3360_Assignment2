package com.example.product_service;

import com.example.product_service.Repository.Product;
import com.example.product_service.Repository.ProductRepository;
import com.example.product_service.Service.FeatureFlagService;
import com.example.product_service.Service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTests {
    @Mock
    private FeatureFlagService featureFlagService;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    // if flag enabled, apply discount to list of products. Else return regular list.
    @Test
    public void premiumPricingTest(){

        Product product = new Product();
        product.setName("Laptop");
        product.setPrice(100);
        product.setQuantity(10);

        var productList = List.of(product);
        when(productRepository.findAll()).thenReturn(productList);

        productService.addProduct(product);
        var regularProducts = productService.getProducts();
        var discountedProducts = productService.getPremiumPricing();
        if(featureFlagService.isPremiumPricingEnabled()){
            assert (regularProducts.getFirst().getPrice()*.9 ==  discountedProducts.getFirst().getPrice());
        } else {
            assert (regularProducts.getFirst().getPrice() ==  discountedProducts.getFirst().getPrice());
        }
    }

}
