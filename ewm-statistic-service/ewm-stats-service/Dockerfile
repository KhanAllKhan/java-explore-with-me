# Stage 1: сборка
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Stage 2: рантайм
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/ewm-stats-service/target/ewm-stats-service-*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
