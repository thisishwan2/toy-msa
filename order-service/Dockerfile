FROM openjdk:17-slim

ARG JAR_FILE=/build/libs/order-service-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} /OrderService.jar

ENTRYPOINT ["java", "-jar", "OrderService.jar"]