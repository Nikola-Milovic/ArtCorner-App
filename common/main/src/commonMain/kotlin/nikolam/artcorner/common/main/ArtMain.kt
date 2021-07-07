package nikolam.artcorner.common.main

import com.arkivanov.decompose.RouterState
import com.arkivanov.decompose.value.Value

interface ArtMain {

    val models: Value<Model>

    val routerState: Value<RouterState<*, Child>>

    fun navigateToNewGroup()

    data class Model(
        val items: List<GroupItem>,
        val text: String
    )

    sealed class Child {
        object NewGroup : Child()
        object GroupDetails : Child()
        object Nothing : Child()
    }
}
