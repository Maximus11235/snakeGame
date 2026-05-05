package meu.projeto

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.http.content.*
import java.io.File

fun main() {
    println("Iniciando servidor Snake.kt no JDK 21...")
    
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        routing {
            // Serve tudo o que estiver em src/main/resources/static
            staticResources("/", "static") {
                default("index.html") // Define o index.html como página inicial
            }
        }
    }.start(wait = true)
}