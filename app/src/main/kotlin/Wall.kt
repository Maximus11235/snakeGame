class Wall{
    val blocos = mutableListOf<Posicao>()

    fun construirBordas(largura: Int, altura: Int){
        for (x in 0 until largura) {
            blocos.add(Posicao(x,0))
            blocos.add(Posicao(x, altura-1))
        }
        for (y in 0 until altura) {
            blocos.add(Posicao(0,y))
            blocos.add(Posicao(largura-1,y))
        }
    }
}