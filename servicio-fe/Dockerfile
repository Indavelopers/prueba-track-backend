# Use a Maven image to build the application
FROM maven:3.8.5-openjdk-11 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package

# Use a lightweight Java runtime image for the final image
FROM eclipse-temurin:11-jre-focal
WORKDIR /app
COPY --from=build /app/target/servicio-fe-1.0.0-SNAPSHOT-fat.jar .
EXPOSE 8080
CMD ["java", "-jar", "servicio-fe-1.0.0-SNAPSHOT-fat.jar"]