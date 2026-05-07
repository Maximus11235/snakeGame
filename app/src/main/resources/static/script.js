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