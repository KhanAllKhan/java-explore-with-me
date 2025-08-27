FROM openjdk:21-jdk-slim
WORKDIR /app

# Копируем собранный jar из target
COPY ewm-statistic-service/ewm-stats-service/target/ewm-stats-service-*.jar app.jar

EXPOSE 9090
ENTRYPOINT ["java","-jar","/app/app.jar"]
