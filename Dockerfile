FROM openjdk:17-jdk-slim

RUN useradd -m appuser

WORKDIR /app

COPY target/findmypet-0.0.1-SNAPSHOT.jar app.jar

USER appuser

ENV APP_ENV=production

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
