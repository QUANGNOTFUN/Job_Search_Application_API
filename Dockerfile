# Giai đoạn build
FROM gradle:8.7-jdk17 AS builder
WORKDIR /app
COPY build.gradle.kts settings.gradle.kts ./
COPY src ./src
RUN gradle build --no-daemon

# Giai đoạn chạy
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar app.jar
RUN mkdir -p /app/uploads
VOLUME /app/uploads
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]