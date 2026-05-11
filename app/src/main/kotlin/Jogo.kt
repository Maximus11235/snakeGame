import kotlinx.serialization.*
import kotlinx.serialization.json.*

@Serializable
data class EstadoDoJogo(val cobra: List<Posicao>, val comida: Posicao)

class Jogo(val largura : Int,val altura : Int)
{
    val snake = Snake(Posicao(largura/2 ,altura/2))
    val wall = Wall().apply{construirBordas(largura,altura)}
    var morango = Morango(Posicao(0,0))

    init {
        gerarNovoMorango()
    }

    fun verificarColisoes()
    {
        if (snake.cabeca in wall.blocos)
        {
            gameOver()
            return
        }
        if (snake.cabeca in snake.corpo)
        {
            gameOver()
            return
        }
        if(snake.cabeca==morango.posicao)
        {
            snake.upGrade()
            gerarNovoMorango()
        }
    }

    private fun gerarNovoMorango()
    {
        var novaPosicao : Posicao
        var posicaoInvalida : Boolean

        do
        {
            val randomX=(0 until largura).random()
            val randomY=(0 until altura).random()
            novaPosicao=Posicao(randomX,randomY)
            
            val naParede=novaPosicao in wall.blocos
            val noCorpo=novaPosicao in snake.blocos
            val naCabeca==novaPosicao==snake.cabeca
            
            posicaoInvalida= naParede || noCorpo || naCabeca
        }while(posicaoInvalida)

        morango.posicao=novaPosicao
    }
    private fun gameOver()
    {
        println("fim de jogo")
    }
}