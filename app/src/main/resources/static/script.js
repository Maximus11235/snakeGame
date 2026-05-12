// 1. Conecta ao WebSocket do Ktor
const socket = new WebSocket("ws://localhost:8080/game");

// Configurações do Canvas (buscamos apenas uma vez para economizar processamento)
const canvas = document.getElementById("gameCanvas");
const contexto = canvas.getContext("2d");
const tamanhoBloco = 20; // Tamanho da nossa grade lógica

// Evento: Quando a conexão dá certo
socket.onopen = function(event) {
    console.log("🟢 Conectado ao servidor da Cobra!");
};

// Evento: Quando a conexão cai
socket.onclose = function(event) {
    console.log("🔴 Conexão encerrada com o servidor.");
};

// Evento: ÚNICO onmessage para gerenciar o estado que chega do servidor
socket.onmessage = function(event) {
    const estado = JSON.parse(event.data);
    
    // Atualiza o texto da pontuação no HTML
    document.getElementById("score").innerText = estado.pontuacao;

    if (estado.gameOver) {
        // Mostra a tela de derrota
        document.getElementById("telaGameOver").style.display = "block";
    } else {
        // Esconde a tela de derrota e desenha o estado atual
        document.getElementById("telaGameOver").style.display = "none";
        desenharJogo(estado.cobra, estado.comida);
    }
};

// Função responsável APENAS por pintar os pixels no Canvas
function desenharJogo(cobra, comida) {
    // 1. Apaga a tela inteira (Descomentado! Crucial para a cobra não deixar rastro)
    contexto.clearRect(0, 0, canvas.width, canvas.height);

    const larguraGrade = 40; 
    const alturaGrade = 40;

    contexto.fillStyle = "gray"; // Substitua "gray" pela cor ou código Hex que desejar
    
    // Desenha as bordas superior e inferior
    for (let x = 0; x < larguraGrade; x++) {
        contexto.fillRect(x * tamanhoBloco, 0, tamanhoBloco, tamanhoBloco); // Topo
        contexto.fillRect(x * tamanhoBloco, (alturaGrade - 1) * tamanhoBloco, tamanhoBloco, tamanhoBloco); // Base
    }
    // Desenha as bordas laterais
    for (let y = 0; y < alturaGrade; y++) {
        contexto.fillRect(0, y * tamanhoBloco, tamanhoBloco, tamanhoBloco); // Esquerda
        contexto.fillRect((larguraGrade - 1) * tamanhoBloco, y * tamanhoBloco, tamanhoBloco, tamanhoBloco); // Direita
    }

    // 2. Pinta a comida de vermelho
    contexto.fillStyle = "red";
    contexto.fillRect(comida.x * tamanhoBloco, comida.y * tamanhoBloco, tamanhoBloco, tamanhoBloco);

    // 3. Pinta a cobra de verde
    contexto.fillStyle = "green";
    cobra.forEach(parte => {
        contexto.fillRect(parte.x * tamanhoBloco, parte.y * tamanhoBloco, tamanhoBloco, tamanhoBloco);
    });

}

// 2. Escuta as teclas do teclado
document.addEventListener("keydown", function(event) {
    let comando = "";

    // Mapeia a tecla apertada para uma palavra simples
    switch(event.key) {
        case "ArrowUp":
        case "w":
        case "W":
            comando = "UP";
            break;
        case "ArrowDown":
        case "s":
        case "S":
            comando = "DOWN";
            break;
        case "ArrowLeft":
        case "a":
        case "A":
            comando = "LEFT";
            break;
        case "ArrowRight":
        case "d":
        case "D":
            comando = "RIGHT";
            break;
    }

    // Se apertou uma tecla válida e o túnel estiver aberto, envia!
    if (comando !== "" && socket.readyState === WebSocket.OPEN) {
        console.log("🕹️ Enviando: " + comando);
        socket.send(comando); 
    }
});

// Quando o botão for clicado no HTML (Tela de Game Over):
function reiniciarJogo() {
    socket.send("RESTART");
}