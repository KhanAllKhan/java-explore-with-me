
FROM maven:3.8.8-jdk-21-slim AS build
WORKDIR /app

COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests


FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

COPY --from=build /app/target/*.jar app-ewm-api.jar

ENTRYPOINT ["java", "-jar", "app-ewm-api.jar"]
