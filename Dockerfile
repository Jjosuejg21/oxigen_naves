# Stage 1: Build
FROM maven:3.9.6-amazoncorretto-21 AS build
COPY pom.xml /app/
COPY src /app/src
WORKDIR /app
RUN mvn clean package -DskipTests

# Stage 2: Run
FROM amazoncorretto:21
COPY --from=build /app/target/naves-espaciales-0.0.1-SNAPSHOT.jar /usr/local/lib/naves-espaciales.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/usr/local/lib/naves-espaciales.jar"]

