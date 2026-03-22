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


---
