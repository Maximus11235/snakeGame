import java.util.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.http.content.*
import java.io.File
import io.ktor.server.websocket.*
import io.ktor.websocket.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.isActive
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

fun main() {
    println("Iniciando servidor Snake.kt no JDK 21...")
    
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        install(WebSockets)
        
        routing {
            staticResources("/", "static") {
                default("index.html") 
            }
            
            webSocket("/game") {
                println("Conexão estabelecida!")
                
                // 1. Instancia o jogo para a sessão que acabou de conectar
                var jogo = Jogo(largura = 40, altura = 40)

                // 2. Inicia o Game Loop rodando em paralelo (Coroutine)
                val gameLoop = launch {
                    while (isActive) { // Roda continuamente enquanto o WebSocket estiver aberto
                        jogo.atualizar()
                        
                        // 3. Monta o estado atual extraindo dados do Jogo
                        // Juntamos a cabeça e o corpo em uma lista só para facilitar o front-end
                        val estadoAtual = EstadoDoJogo(
                            cobra = listOf(jogo.snake.cabeca) + jogo.snake.corpo,
                            comida = jogo.morango.posicao,
                            pontuacao = jogo.pontuacaoTotal, // Pegando a pontuação da classe Jogo
                            gameOver = jogo.isGameOver       // Pegando a flag de derrota
                        )
                        
                        // 4. Converte e envia
                        val textoJson = Json.encodeToString(estadoAtual)
                        send(textoJson)
                        
                        // 5. Controla a velocidade da cobra (ex: 200 milissegundos)
                        delay(200) 
                    }
                }

                // 6. O loop principal fica travado aqui apenas escutando os comandos
                try {
                    for (frame in incoming) { 
                        if (frame is Frame.Text) {
                            val receivedText = frame.readText().uppercase()
                            println("Comando recebido: $receivedText")
                            
                            if (receivedText == "RESTART") {
                                // O jogador clicou no botão "Reiniciar" no front-end
                                jogo = Jogo(largura = 40, altura = 40) // Cria um jogo totalmente novo //ALTERAR FUTURAMENTE,ALOCAÇÃO ESTÁTICA
                                println("Jogo reiniciado!")
                                
                            } else if (!jogo.isGameOver) { 
                                // A TRAVA ESTÁ AQUI: Só aceita direção se o jogo NÃO estiver em Game Over
                                
                                when (receivedText) {
                                    "UP" -> if (jogo.snake.direcao != Direcao.DOWN) jogo.snake.direcao = Direcao.UP
                                    "DOWN" -> if (jogo.snake.direcao != Direcao.UP) jogo.snake.direcao = Direcao.DOWN
                                    "LEFT" -> if (jogo.snake.direcao != Direcao.RIGHT) jogo.snake.direcao = Direcao.LEFT
                                    "RIGHT" -> if (jogo.snake.direcao != Direcao.LEFT) jogo.snake.direcao = Direcao.RIGHT
                                }
                            }
                        }
                    }
                } catch (e: Exception) {
                    println("Conexão encerrada: ${e.localizedMessage}")
                } finally {
                    // Quando o jogador fechar a aba do navegador, paramos o loop do jogo
                    gameLoop.cancel() 
                }
            }
        }
    }.start(wait = true)
}