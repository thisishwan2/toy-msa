FROM openjdk:17-slim

ARG JAR_FILE=/build/libs/service-discovery-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} /DiscoveryService.jar

ENTRYPOINT ["java", "-jar", "DiscoveryService.jar"]