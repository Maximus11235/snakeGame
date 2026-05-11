enum class Direcao{ UP, DOWN, LEFT, RIGHT }

class Snake(posicaoInicial: Posicao){
    var cabeca = posicaoInicial
    val corpo = mutableListOf<Posicao>()
    var direcao =  Direcao.RIGHT


    //a função mover serve somente para atualizar o corpo da cobra independente de os dados serem ou não consistentes
    fun mover(nextCabeca: Posicao){
        //isso serve para mover a lista do corpo
        if(corpo.isNotEmpty()){
            //ele vai atualizar os valores
            for(i in corpo.size - 1 downTo 1){
                corpo[i]= corpo[i-1]
            }
            corpo[0] = cabeca
        }
        //a cabeça é atualizada
        cabeca = nextCabeca
    }

    fun upGrade(){
        val posicaoRabo = if(corpo.isNotEmpty()) corpo.last() else cabeca
        corpo.add(posicaoRabo)
    }
}