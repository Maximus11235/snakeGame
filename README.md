<<<<<<< HEAD
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


    sudo usermod -aG docker $USER


    sudo service docker start


    docker compose up -d --build


# Para executar:

Entre no container que contém a aplicação:

    docker exec -it snake-container bash

Compile o codigo como:

    kotlinc test.kt -include-runtime -d test.jar

Execute com:

    java -jar test.jar
=======
olá mundo STROGONOFF
olá mundo feito por maximus parte 7

>>>>>>> a1ec051af06b3722c9dae8f2df8765b3b20014cf
