PROG3360-Sec3-Assignment2_ChhetriHallTurnerRogers

Spring Boot Microservices

- Product Service
- Order Service
   The Order service will call the Product Service to validate product availability before creating an order.
  
- 3 feature flags using Unleash as feature flag management server
  - premium-pricing	-	When enabled, applies a 10% discount to all product prices for premium users
  - order-notifications	-	When enabled, logs order confirmation notifications (simulating email/SMS)
  - bulk-order-discount	-	When enabled, applies a 15% discount when order quantity exceeds 5 items

- CI/CD pipeline that validates feature flag configurations
  
## Setup & Run

- Clone Repo or run from submitted zip file
  
- build docker compose file in the terminal with this command: docker-compose up --build
- / Run	All services via docker-compose up
- Run Product Service
- Run Order Service

- Watch the GitHub Actions workflow run to see the 3 jobs in action


#From Assignment 1
- Test APIs in Postman
- Get All products: http://localhost:8081/api/products
- Get product by ID: http://localhost:8081/api/products/{id}
- Create Product: http://localhost:8081/api/products
- JSON Body {
  "name": "Game",
  "price": 25.00,
  "quantity": 5
  }
- Delete product http://localhost:8081/api/products/{id}
- Get Orders http://localhost:8082/api/orders
- Get Order by ID: http://localhost:8082/api/orders/{id}
- Create Order: http://localhost:8082/api/orders
- JSON Body {
 "productId": 1,
  "quantity": 5,
  "totalPrice": 50,
  "status": "Paid"
  }
