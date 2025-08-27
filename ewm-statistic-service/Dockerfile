# Stage 1: билдим таргетный jar
FROM maven:3.9-eclipse-temurin-21-slim AS builder
WORKDIR /app

# Копируем POMы и докачиваем зависимости
COPY pom.xml .
COPY ewm-statistic-service/pom.xml ewm-statistic-service/
RUN mvn -f ewm-statistic-service/pom.xml dependency:go-offline -B

# Копируем весь исходник и собираем
COPY . .
RUN mvn -pl ewm-statistic-service -am clean package -DskipTests -B

# Stage 2: runtime образ
FROM eclipse-temurin:21-jdk-slim
WORKDIR /app

# Берём готовый Spring-Boot jar из builder
COPY --from=builder /app/ewm-statistic-service/ewm-stats-service/target/ewm-stats-service-*.jar app.jar

EXPOSE 9090
ENTRYPOINT ["java","-jar","/app/app.jar"]
