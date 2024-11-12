# Use a imagem oficial do OpenJDK 23 como base
FROM openjdk:23-jdk-slim

# Instala o bash para manter o shell ativo
RUN apt-get update && apt-get install -y bash

# Define o diretório de trabalho
WORKDIR /app

# Copia o arquivo JAR
COPY target/authorization-server-1.1.jar authorization-server.jar

# Expõe a porta do serviço
EXPOSE 9000

# Mantém o bash ativo
CMD ["bash"]
