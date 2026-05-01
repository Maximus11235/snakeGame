# Usa o Ubuntu 24.04 oficial
FROM ubuntu:24.04

# Evita perguntas chatas na instalação
ENV DEBIAN_FRONTEND=noninteractive

# Atualiza e instala tudo em uma única camada (mais eficiente)
RUN apt-get update && apt-get install -y \
    openjdk-21-jdk \
    kotlin \
    gradle \
    curl \
    git \
    unzip \
    && rm -rf /var/lib/apt/lists/*

# Cria e define a pasta de trabalho DENTRO do Ubuntu
WORKDIR /app


# Porta para o seu jogo Web
EXPOSE 8080

# Mantém o container vivo (o CMD original estava tentando rodar gradle sem o projeto existir)
CMD ["tail", "-f", "/dev/null"]

