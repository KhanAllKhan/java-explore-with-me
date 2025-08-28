# точка входа — только JRE, без Maven
FROM eclipse-temurin:21-jre

WORKDIR /app

# копируем JAR из результатов хост-сборки
COPY ewm-statistic-service/ewm-stats-service/target/ewm-stats-service-*.jar app.jar

EXPOSE 9090

ENTRYPOINT ["java", "-jar", "app.jar"]
