FROM openjdk:17-jdk-slim
 
RUN useradd -m tech-lab-user
 
WORKDIR /app
 
COPY target/*.jar app.jar
 
USER tech-lab-user
 
ENV APP_ENV=production
 
EXPOSE 8080
 
ENTRYPOINT ["java", "-jar", "app.jar"]