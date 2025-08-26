
FROM maven:3.8.8-jdk-21-slim AS build
WORKDIR /srv
COPY pom.xml .
COPY ewm-stats-client/pom.xml ./ewm-stats-client/
COPY ewm-stats-dto/pom.xml    ./ewm-stats-dto/
COPY ewm-stats-service/pom.xml ./ewm-stats-service/
COPY ewm-stats-client/src ./ewm-stats-client/src
COPY ewm-stats-dto/src    ./ewm-stats-dto/src
COPY ewm-stats-service/src ./ewm-stats-service/src

RUN mvn clean package -pl ewm-stats-service -am -DskipTests


FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=build /srv/ewm-stats-service/target/*.jar app-ewm-stats.jar

ENTRYPOINT ["java", "-jar", "app-ewm-stats.jar"]
