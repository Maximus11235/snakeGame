# Snake Game

Snake game é um jogo livre para todas as idades que tem como função principal testar a 
capacidade mental e resistencia emocional dos desenvolvedores que acham que sabem de algo

# Requisitos:
- WSL2
- UBUNTU 22.04
- UM COMPUTADOR??
- PACIENCIA

# Para rodar o container rode os blocos no terminal da pasta desejada:

    sudo apt-get update
    sudo apt-get install ca-certificates curl
    sudo install -m 0755 -d /etc/apt/keyrings
    sudo curl -fsSL https://download.docker.com/linux/ubuntu/gpg -o /etc/apt/keyrings/docker.asc
    sudo chmod a+r /etc/apt/keyrings/docker.asc


    echo \
    "deb [arch=$(dpkg --print-architecture) signed-by=/etc/apt/keyrings/docker.asc] https://download.docker.com/linux/ubuntu \
    $(. /etc/os-release && echo "$VERSION_CODENAME") stable" | \
    sudo tee /etc/apt/sources.list.d/docker.list > /dev/null
    sudo apt-get update


    sudo apt-get install docker-ce docker-ce-cli containerd.io docker-buildx-plugin docker-compose-plugin


    newgrp docker (Ele cria um novo shell "dentro" do atual. Se você digitar exit,
                   voltará para o shell anterior onde o grupo ainda não está ativo.) 


    sudo service docker start


    docker compose up -d --build 

    


# Para executar:
Acorde o container com:

    docker compose start

Para entrar no container que contém a aplicação:

    docker exec -it snake-container bash

Para criar a estrutura dos arquivos, o wrapper do Gradle e os arquivos de build iniciais:

    gradle init --type kotlin-application --dsl kotlin --package meu.projeto --project-name ktor-web

Aparecerá:

    Enter target Java version (min: 7, default: 21): 21
    Select application structure:  1: Single application project  2: Application and library project
    Enter selection (default: Single application project) [1..2] 1
    Generate build using new APIs and behavior (some features may change in the next minor release)? (default: no) [yes, no] no
    Task :initTo learn more about Gradle by exploring our Samples at 

Para rodar o gradle:

    ./gradlew :app:run

Abra a porta disponível no navegador (se a sua porta estiver sendo usada por outra aplicação, 
apresentará erros)
