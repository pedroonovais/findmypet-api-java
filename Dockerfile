FROM openjdk:17-jdk-slim
 
RUN useradd -m appuser
 
WORKDIR /app
 
USER appuser
 
ENV APP_ENV=production
 
EXPOSE 8080
 
ENTRYPOINT ["java", "-jar", "app.jar"]