// 1. Conecta ao WebSocket do Ktor
// O navegador sabe que deve procurar a porta 8080 no localhost
const socket = new WebSocket("ws://localhost:8080/game");

// Evento: Quando a conexão dá certo
socket.onopen = function(event) {
    console.log("🟢 Conectado ao servidor da Cobra!");
};

// Evento: Quando a conexão cai
socket.onclose = function(event) {
    console.log("🔴 Conexão encerrada com o servidor.");
};

// Evento: Quando o servidor manda alguma coisa de volta
socket.onmessage = function(event) {
    console.log("📩 Servidor diz: ", event.data);
    // Pega o canvas que está no seu index.html
    const canvas = document.getElementById("gameCanvas");
    const contexto = canvas.getContext("2d");
    const tamanhoBloco = 20; // Tamanho da nossa grade lógica

    //socket.onmessage = function(event) {
    // 1. O texto do Kotlin chega aqui e é convertido de volta para objeto
        const jogo = JSON.parse(event.data); 

    // 2. Apaga a tela inteira (o quadro negro)
        //contexto.clearRect(0, 0, canvas.width, canvas.height);

    // 3. Pinta a comida de vermelho
        contexto.fillStyle = "red";
        contexto.fillRect(jogo.comida.x * tamanhoBloco, jogo.comida.y * tamanhoBloco, tamanhoBloco, tamanhoBloco);

    // 4. Pinta a cobra de verde
        contexto.fillStyle = "green";
        jogo.cobra.forEach(parte => {
            contexto.fillRect(parte.x * tamanhoBloco, parte.y * tamanhoBloco, tamanhoBloco, tamanhoBloco);
        });
//};
};

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