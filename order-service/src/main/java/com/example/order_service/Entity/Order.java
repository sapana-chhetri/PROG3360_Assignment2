package com.example.order_service.Entity;
import jakarta.persistence.*;

@Entity
@Table(name="orders")
    public class Order {

        @Id
        @GeneratedValue(strategy= GenerationType.IDENTITY)
        private int id;
        private int productId;
        private int quantity;
        private double totalPrice;
        private String status;

        public Order() {}

        // Order Constructor
        public Order(int productId, int quantity, String status, double totalPrice) {
            this.productId = productId;
            this.quantity = quantity;
            this.totalPrice = totalPrice;
            this.status = status;
        }

        public int getId() {return id;}

        public void setId(int id) {this.id = id;}

        public int getProductId() {return productId;}

        public void setProductId(int productId) {this.productId = productId;}

        public int getQuantity() {return quantity;}

        public void setQuantity(int quantity) {this.quantity = quantity;}

        public double getTotalPrice() {return totalPrice;}

        public void setTotalPrice(double totalPrice) {this.totalPrice = totalPrice;}

        public String getStatus() {return status;}

        public void setStatus(String status) {this.status = status;}


    }


