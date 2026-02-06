package com.example.product_service.Service;

import com.example.product_service.Repository.Product;
import com.example.product_service.Repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    //It handles business logic for managing product and communicates with the Repository
    // basic CRUD operations using repository
    private ProductRepository productRepository;
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    //Calls the repository method findAll() to fetch all products
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(int id) {
        return productRepository.findById(id);
    }

    public boolean addProduct(Product product) {
        productRepository.save(product);
        return true;
    }

    public boolean deleteProduct(int id) {
        try{
            productRepository.delete(productRepository.findById(id).orElseThrow(()->new Exception("Product not found")));
            return true;
        } catch (Exception e){
            return false;
        }
    }

}

