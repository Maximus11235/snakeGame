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
            // Serve o index.html na raiz
            get("/") {
                val file = File("index.html")
                if (file.exists()) {
                    call.respondFile(file)
                } else {
                    // Tenta o caminho relativo caso esteja dentro da pasta app
                    call.respondFile(File("../index.html"))
                }
            }

            // Permite que o HTML carregue scripts, imagens e CSS da pasta raiz
            staticFiles("/", File(".")) 
            staticFiles("/", File("../"))
        }
    }.start(wait = true)
}