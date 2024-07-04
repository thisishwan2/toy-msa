FROM openjdk:17-slim

ARG JAR_FILE=/build/libs/config-service-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} /ConfigService.jar

ENTRYPOINT ["java", "-jar", "ConfigService.jar"]