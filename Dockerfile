FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/employee-api-0.0.1-SNAPSHOT.jar /app/employee-api.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/employee-api.jar"]

