FROM openjdk:17-slim

ARG JAR_FILE=/build/libs/user-service-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} /UserService.jar

ENTRYPOINT ["java", "-jar", "UserService.jar"]