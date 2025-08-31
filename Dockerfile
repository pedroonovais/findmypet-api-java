# Etapa 1: Build com Maven
FROM maven:3.9.5-eclipse-temurin-17 AS builder
WORKDIR /app

# Copia os arquivos de configuração primeiro (melhora cache do Docker)
COPY pom.xml .
COPY .mvn/ .mvn
COPY mvnw .
RUN ./mvnw dependency:go-offline -B

# Copia o restante do código e gera o JAR
COPY src ./src
RUN ./mvnw clean package -DskipTests

# Etapa 2: Runtime com JDK slim
FROM openjdk:17-jdk-slim

# Cria usuário sem privilégios
RUN useradd -m tech-lab-user
WORKDIR /app

# Copia o JAR gerado na etapa anterior
COPY --from=builder /app/target/*.jar app.jar

# Define usuário de execução
USER tech-lab-user

# Variáveis de ambiente
ENV APP_ENV=production

# Porta exposta
EXPOSE 8080

# Comando de entrada
ENTRYPOINT ["java", "-jar", "app.jar"]
