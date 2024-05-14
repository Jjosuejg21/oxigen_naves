FROM ubuntu:latest
LABEL authors="jj"

ENTRYPOINT ["top", "-b"]
#Build
FROM maven:3.9.6-openjdk-21 AS build
COPY pom.xml /app/
COPY src /app/src
WORKDIR /app
RUN mvn clean package -DskipTests

#Run
FROM openjdk:21-jdk-slim
COPY --from=build /app/target/navesEspaciales-api-0.0.1-SNAPSHOT.jar /usr/local/lib/navesEspaciales-api.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/usr/local/lib/navesEspaciales-api.jar"]
