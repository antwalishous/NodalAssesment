FROM openjdk:8-alpine
WORKDIR /app
COPY target/s2-0.0.1-SNAPSHOT.jar s2Service.jar
# runs app
ENTRYPOINT  ["java", "-jar","s2Service.jar"]