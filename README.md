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

# Assignment 3

# How to start minikube cluster & pods

### Before you begin

Make sure Minikube is installed/pulled on your docker hub. I did this by just running the command ```minikube start``` inside a PowerShell terminal.

## Step 1: Create kubernetes instance

Inside of your docker hub, go to the *Kubernetes* tab. If you haven't installed it yet, Docker will give you the option.

Once Kubernetes is installed, you should see a "default" namespace. We will *not* be using default.

## Step 2: Create namespace

Open a terminal within IntelliJ, using the project root. I recommend changing to the ./k8s directory for the following steps.

```cd ./k8s```

From here, you can run the following command to instantiate our assignment 3 namespace:

```kubectl create -f .\k8s\namespace.yaml```

To make life easier, run this command to create an alias for the namespace. Instead of "assignment3", you can just write "a3" into the terminal afterwards.

```kubectl config set-context a3 --namespace=assignment3```

## Step 3: Namespace configuration

To properly configure our new namespace, run these commands:

```kubectl config set-context a3 --cluster=minikube```
```kubectl config set-context a3 --user=minikube``` 

Now, a3 is using minikube.

## Step 4: Use the Assignment 3 namespace

We've created the namespace "a3", but if you try to run Kubernetes commands, you will still be inside of the "default" namespace that we saw earlier.

To use "a3" instead, run this command:

```kubectl config use-context a3```

## Step 5: Create product and order service deployment

We now need to instantiate our product and order service image containers into a "pod".

```kubectl apply -f .\product-deployment.yaml```
```kubectl apply -f .\order-deployment.yaml```


After running that command, I recommend using ```kubectl get deployments``` to check that the service is working. You should see the following output:

NAME              READY   UP-TO-DATE   AVAILABLE   AGE
product-service   2/2     2            2           13m
product-service   2/2     2            2           13m


# Part 2 ReplicaSets, Scaling, and Self-Healing 

##  1 To show the deployment
    kubectl get deployments -n assignment3

##  2 Show ReplicaSets
    kubectl get rs -n assignment3
    
##  3 Scale order-service, product service
    kubectl scale deployment order-service --replicas=4 -n assignment3
    kubectl scale deployment product-service --replicas=4 -n assignment3
    kubectl get pods -n assignment3 -w
    
##  4 Delete one pod
    kubectl delete pod <pod-name> -n assignment3
    
##  5 Show that Kubernetes automatically creates a replacement Pod
    kubectl get pods -n assignment3 -w


# Part 3 Rolling Update Demonstration 

### Commands for Changing Docker Image Tag for Product Service. 

## Step 1: Original Version is running (V1)

No changes made, running as is.

## Step 2: Update the Deployment to Version 2 - Change Docker image tag to Version 2 (:v2)

Build the V2 Docker Image

```docker build -t prog3360_assignment2-product-service:v2 .```

Make it available to Minikube

```minikube image load prog3360_assignment2-product-service:v2```

Update the image of the application to V2

```kubectl set image deployment/product-service product-service=prog3360_assignment2-product-service:v2 -n assignment3```

## Step 3: Verify Rollout Succeeds

```kubectl rollout status deployment/product-service -n assignment3```

## Step 4: Check Rollout History

```kubectl rollout history deployment/product-service -n assignment3```

## Step 5: Show the new version is available

Show pods are running

```kubectl get pods -n assignment3```

Describe the Product service pod
*Get the pod name from the above command, i.e. product-service-688656bb69-894k

```kubectl describe pod <pod-name> -n assignment3```

Look for new image tag, for example.. Image: prog3360_assignment2-product-service:v2

## Step 6: Bonus Rollback Deployment to Previous Version

```kubectl rollout undo deployment/product-service -n assignment3```

---

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

