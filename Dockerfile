FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY target/calculator-0.0.1-SNAPSHOT.jar app.jar
COPY config.yml .

EXPOSE 8080

CMD ["java", "-jar", "app.jar", "server", "config.yml"]