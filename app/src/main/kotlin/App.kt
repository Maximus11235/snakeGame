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
fun main() {
    println("Iniciando servidor Snake.kt no JDK 21...")
    
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        install(WebSockets)
        routing {
            // Serve tudo o que estiver em src/main/resources/static
            staticResources("/", "static") {
                default("index.html") // Define o index.html como página inicial
            }
            
             webSocket("/game"){
                println("Conexão estabelecida!")
                try {
                // Aqui o servidor fica "escutando" eventos do navegador
                    for (frame in incoming) {
                        if (frame is Frame.Text) {
                            val receivedText = frame.readText()
                            // Evento de entrada (POE): Tecla pressionada no JS
                            println("Comando recebido: $receivedText")

                            // Lógica de POO: Atualizar a direção do objeto Snake
                            // Enviar resposta: send("estado_do_jogo_em_json")
                            }
                        }
                } catch (e: Exception) {
                    println("Conexão encerrada: ${e.localizedMessage}")
                }
             }
        }
    }.start(wait = true)
}