FROM openjdk:17-slim

ARG JAR_FILE=/build/libs/catalog-service-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} /CatalogService.jar

ENTRYPOINT ["java", "-jar", "CatalogService.jar"]