import kotlinx.serialization.*
import kotlinx.serialization.json.*

@Serializable
data class EstadoDoJogo(val cobra: List<Posicao>, val comida: Posicao)