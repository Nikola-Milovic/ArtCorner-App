package nikolam.artcorner.common.main

import com.arkivanov.decompose.RouterState
import com.arkivanov.decompose.value.Value
import nikolam.artcorner.common.create.ArtCreate
import nikolam.artcorner.common.details.ArtDetails

interface ArtMain {

    val models: Value<Model>

    val routerState: Value<RouterState<*, Child>>

    fun navigateToCreateGroup()
    fun navigateToDetailsGroup(id : Long)

    data class Model(
        val items: List<GroupItem>,
        val text: String
    )

    sealed class Child {
        data class Create(val component : ArtCreate) : Child()
        data class Details(val component : ArtDetails) : Child()
        object Nothing : Child()
    }
}
