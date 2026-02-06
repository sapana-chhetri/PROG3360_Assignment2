FROM eclipse-temurin:21-jre

WORKDIR /product-service

COPY target/*-SNAPSHOT.jar app.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "app.jar"]