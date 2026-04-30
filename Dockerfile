# Usa o Ubuntu 24.04 oficial
FROM ubuntu:24.04

# Evita perguntas chatas na instalação
ENV DEBIAN_FRONTEND=noninteractive

<<<<<<< HEAD
# Atualiza e instala tudo em uma única camada (mais eficiente)
RUN apt-get update && apt-get install -y \
    openjdk-17-jdk \
    kotlin \
    gradle \
    curl \
    git \
    unzip \
=======
# Atualiza e instala o essencial: Java 17, Kotlin e ferramentas de rede/zip
RUN apt-get update && apt-get install -y \
    openjdk-17-jdk \
    kotlin \
    curl \
    git \
>>>>>>> a1ec051af06b3722c9dae8f2df8765b3b20014cf
    && rm -rf /var/lib/apt/lists/*

# Cria e define a pasta de trabalho DENTRO do Ubuntu
WORKDIR /app

<<<<<<< HEAD
# Porta para o seu jogo Web
EXPOSE 8080

# Mantém o container vivo (o CMD original estava tentando rodar gradle sem o projeto existir)
CMD ["tail", "-f", "/dev/null"]
=======
# Mantém o terminal aberto
CMD ["bash"]
>>>>>>> a1ec051af06b3722c9dae8f2df8765b3b20014cf
