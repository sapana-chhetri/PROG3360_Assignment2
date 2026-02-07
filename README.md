PROG3360-Sec3-Assignment1_ChhetriHallTurnerRogers

Spring Boot Microservices

- Product Service
- Order Service

## Setup & Run

- Clone Repo or run from submitted zip file
  
- build docker compose file in the terminal with this command: docker-compose up --build
  
- Run Product Service
- Run Order Service
  
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
