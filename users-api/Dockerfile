FROM maven:3.6.3-jdk-8 AS build
# Set working directory
WORKDIR /app
COPY . .
ENV MAVEN_CONFIG=
# Use the Maven Wrapper and specify the local repo
RUN ./mvnw clean install -Dmaven.repo.local=/app/.m2

FROM openjdk:8-jre-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar /app/target/
ENV SERVER_PORT=8083
# Set the entrypoint to run the application
ENTRYPOINT ["java", "-jar", "target/users-api-0.0.1-SNAPSHOT.jar"]