# Use a imagem oficial do OpenJDK 23 como base
FROM openjdk:23-jdk-slim

RUN apt-get update && apt-get install -y bash

WORKDIR /app

COPY target/client-noauth-application-1.1.jar client-noauth-application.jar

EXPOSE 8085

CMD ["bash"]
