FROM openjdk:21-jdk AS jdk

FROM maven:3.9.4 AS maven

COPY --from=jdk /opt/java/openjdk /opt/java/openjdk

ENV JAVA_HOME=/opt/java/openjdk
ENV PATH="$JAVA_HOME/bin:$PATH"

FROM maven:3.9.4 AS build
COPY pom.xml /app/
COPY src /app/src
WORKDIR /app
RUN mvn clean package -DskipTests

FROM openjdk:21-jdk-slim
COPY --from=build /app/target/naves-espaciales-0.0.1-SNAPSHOT.jar /usr/local/lib/naves-espaciales.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/usr/local/lib/naves-espaciales.jar"]
