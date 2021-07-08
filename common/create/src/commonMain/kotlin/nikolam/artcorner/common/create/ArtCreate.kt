package nikolam.artcorner.common.create

import com.arkivanov.decompose.value.Value

interface ArtCreate {

    val models: Value<Model>

    fun closeCreation()
    fun finishCreation()

    data class Model(
        val text: String
    )

    sealed class Output {
        object Closed : Output()
    }
}
