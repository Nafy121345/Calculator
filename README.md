# Coin Calculator Application

This project is a Java-based application that calculates the minimum number of coins needed to make up a target amount. 
It uses Dropwizard for the backend and React for the frontend.

## Prerequisites

- Docker


## Directory Structure

- `target/`: Contains the compiled JAR file for the backend.
- `frontend/`: Contains the React frontend application.
- `config.yml`: Configuration file for the Dropwizard application.

- ## Dockerfile-backend

FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY target/calculator-0.0.1-SNAPSHOT.jar app.jar
COPY config.yml .

EXPOSE 8080

CMD ["java", "-jar", "app.jar", "server", "config.yml"]

- ## Dockerfile-frontend
FROM node:18 AS build

WORKDIR /app

COPY package*.json ./

RUN npm install

COPY . .

RUN npm run build

FROM nginx:alpine

COPY --from=build /app/build /usr/share/nginx/html

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]

## Instruction
Building the Docker Images
1. Clone the repository and navigate to the project directory.
2. Build the Docker images for both the backend and frontend:

Running the Containers
1. Start the containers using Docker:

This will start the backend and frontend services, and the web will be accessible at http://localhost:3000.

## Notes
1. Ensure that the target/calculator-0.0.1-SNAPSHOT.jar file exists before building the Docker image.
2. The frontend build process (npm install && npm run build) will be executed every time the frontend container starts. You may want to optimize this for production use.
3. Adjust the JAVA_OPTS environment variable as needed for your application's performance requirements.
4. If an error occurs during access, please check your network settings and firewall.Run the docker ps command to ensure that the frontend container is running.
   
For any issues or questions, please refer to the project documentation or contact the maintainers.
