# Use an official Maven image to build the application
FROM maven:3.8-openjdk-11 AS build

# Set the working directory
WORKDIR /app

# Copy the pom.xml and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the source code
COPY MainVerticle.java ./src/main/java/com/example/MainVerticle.java

# Compile the application
RUN mvn compile

# Expose the port the application runs on
EXPOSE 8080

# Command to run the application
CMD ["mvn", "exec:java"]
