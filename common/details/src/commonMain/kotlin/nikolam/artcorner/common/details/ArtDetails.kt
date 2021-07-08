package nikolam.artcorner.common.details

import com.arkivanov.decompose.value.Value

interface ArtDetails {

    val models: Value<Model>

    fun closeDetails()

    data class Model(
        val text: String
    )

    sealed class Output {
        object Closed : Output()
    }
}
