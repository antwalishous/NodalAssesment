
FROM openjdk:8-alpine
WORKDIR /app
COPY target/s1-0.0.1-SNAPSHOT.jar s1Service.jar
# runs app
ENTRYPOINT  ["java", "-jar","s1Service.jar"]