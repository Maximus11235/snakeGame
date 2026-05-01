package meu.projeto
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.http.content.*
import java.io.File

fun main() {
    // host 0.0.0.0 é obrigatório para o Docker expor a porta
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        routing {
            // Rota principal: serve o arquivo index.html que está na raiz
            get("/") {
                val file = File("../index.html") // Sobe um nível pois o projeto está na pasta /app
                if (file.exists()) {
                    call.respondFile(file)
                } else {
                    call.respondText("Arquivo index.html não encontrado na raiz!")
                }
            }
            
            // Rota de API de exemplo para você testar se o Kotlin está processando
            get("/api/status") {
                call.respondText("Kotlin rodando no JDK 21 via Docker!")
            }
        }
    }.start(wait = true)
}