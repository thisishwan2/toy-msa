FROM openjdk:17-slim

ARG JAR_FILE=/build/libs/apigateway-service-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} /ApigatewayService.jar

ENTRYPOINT ["java", "-jar", "ApigatewayService.jar"]