package com.example.product_service.Controller;

import com.example.product_service.Repository.Product;
import com.example.product_service.Service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ProductController {
    private ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/test")
    public boolean test() {
        return productService.flagTest();
    }

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productService.getProducts();
    }

    @GetMapping("/products/premium")
    public List<Product> getPremiumPricingProducts() {
        return productService.getPremiumPricing();
    }

    @GetMapping("/products/{id}")
    public Optional<Product> getProductById(@PathVariable int id) {
        return productService.getProductById(id);
    }

    @PostMapping("/products")
    public String addProduct( @RequestBody Product product) {
        if(productService.addProduct(product)) {
            return "Product added successfully";
        }
        else{
            return "Product not added";
        }
    }


    @DeleteMapping("/products/{id}")
    public String deleteProduct(@PathVariable int id) {
        if(productService.deleteProduct(id)){
            return "Product deleted successfully";
        }  else {
            return "Product not deleted.";
        }
    }


}


