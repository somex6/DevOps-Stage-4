# Use the official OpenJDK image as the base image
FROM maven:3.9.6-amazoncorretto-21 AS build

# Set working directory
WORKDIR /app
# RUN apt-get update && \
#     apt-get install -y curl && \
#     curl -O https://archive.apache.org/dist/maven/maven-3/3.9.6/binaries/apache-maven-3.9.6-bin.tar.gz && \
#     tar -xzvf apache-maven-3.9.6-bin.tar.gz -C /usr/local && \
#     ln -s /usr/local/apache-maven-3.9.6/bin/mvn /usr/bin/mvn
COPY . .
# COPY pom.xml mvnw .mvn mvnw.cmd /app/

# Download all dependencies (separate layer to use Docker cache)
# RUN ./mvnw dependency:go-offline -B

# Copy the project source
# COPY src src
# RUN mvn dependency:go-offline -B
# Package the application
ENV MAVEN_CONFIG=
# Use the Maven Wrapper and specify the local repo
# RUN ./mvnw clean install -Dmaven.repo.local=/app/.m2
RUN ./mvnw clean install

# RUN ./mvnw package -DskipTests

# Use a smaller runtime image
FROM eclipse-temurin:21-jre
# Copy the JAR file from the build stage
WORKDIR /app

COPY --from=build /app/target/*.jar /app/target/
# COPY . .
ENV JWT_SECRET=foo
ENV SERVER_PORT=8083

# Set the entrypoint to run the application
ENTRYPOINT ["java", "-jar", "target/users-api-0.0.1-SNAPSHOT.jar"]


# Add healthcheck
# HEALTHCHECK --interval=30s --timeout=3s CMD curl -f http://localhost:8080/actuator/health || exit 1