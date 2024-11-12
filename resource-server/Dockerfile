# Use a imagem oficial do OpenJDK 23 como base
FROM openjdk:23-jdk-slim

RUN apt-get update && apt-get install -y bash

WORKDIR /app

COPY target/resource-server-1.1.jar resource-server.jar

EXPOSE 8082

CMD ["bash"]
