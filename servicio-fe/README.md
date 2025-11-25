# Servicio FE (Java Vert.x)

This is a simple "Hello World" web application built with Java and the Vert.x toolkit.

## Prerequisites

- Java Development Kit (JDK) 11 or newer.
- Apache Maven.

## How to Run Locally

1. **Run the application using Maven:**
    This command will compile the code and start the Vert.x server.

    ```bash
    mvn compile exec:java
    ```

2. **Test the application:**
    Once the server is running, open your web browser or use `curl` to access the endpoint:

    ```bash
    curl http://localhost:8080
    ```

    You should see the message: `Hello from Vert.x!`
