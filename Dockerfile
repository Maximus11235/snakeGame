# Usa o Ubuntu 24.04 oficial
FROM ubuntu:24.04

# Evita perguntas chatas na instalação
ENV DEBIAN_FRONTEND=noninteractive

# Definição de versões para facilitar manutenção
ENV GRADLE_VERSION=8.7
ENV GRADLE_HOME=/opt/gradle

# Atualiza e instala dependências essenciais
RUN apt-get update && apt-get install -y \
    openjdk-21-jdk \
    kotlin \
    curl \
    git \
    unzip \
    && rm -rf /var/lib/apt/lists/*

# Instalação manual do Gradle (Forma correta para garantir compatibilidade com JDK 21)
RUN curl -L https://services.gradle.org/distributions/gradle-${GRADLE_VERSION}-bin.zip -o gradle.zip \
    && unzip gradle.zip \
    && mv gradle-${GRADLE_VERSION} ${GRADLE_HOME} \
    && rm gradle.zip

# Adiciona o Gradle ao PATH do sistema
ENV PATH=$PATH:${GRADLE_HOME}/bin

# Cria e define a pasta de trabalho
WORKDIR /app

# Porta para o seu servidor Web
EXPOSE 8080

# Mantém o container vivo
CMD ["tail", "-f", "/dev/null"]