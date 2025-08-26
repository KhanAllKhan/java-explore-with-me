FROM openjdk:17-jdk-slim
WORKDIR /app
COPY ewm-statistic-service/ewm-stats-service/target/ewm-stats-service-*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]