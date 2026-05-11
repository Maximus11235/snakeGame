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

// 1. IMPORTS NOVOS AQUI
import kotlinx.serialization.*
import kotlinx.serialization.json.*

// 2. AS CLASSES FICAM AQUI FORA! (Como se fossem o "molde" do jogo)
@Serializable
data class Posicao(val x: Int, val y: Int)

@Serializable
data class EstadoDoJogo(val cobra: List<Posicao>, val comida: Posicao)

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
                try {
                    for (frame in incoming) { 
                        if (frame is Frame.Text) {
                            val receivedText = frame.readText()
                            println("Comando recebido: $receivedText")
                            
                            // Lógica do jogo vai aqui no futuro (atualizar posição baseado na tecla)

                            // 3. Monta o estado atual para enviar
                            val estadoAtual = EstadoDoJogo(
                                cobra = listOf(Posicao(5, 5), Posicao(4, 5)),
                                comida = Posicao(10, 11)
                            )
                            
                            // 4. Converte e envia
                            val textoJson = Json.encodeToString(estadoAtual)
                            send(textoJson)
                        }
                    }
                } catch (e: Exception) {
                    println("Conexão encerrada: ${e.localizedMessage}")
                }
            }
        }
    }.start(wait = true)
}