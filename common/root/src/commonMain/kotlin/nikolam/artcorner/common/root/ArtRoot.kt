package nikolam.artcorner.common.root

import com.arkivanov.decompose.RouterState
import com.arkivanov.decompose.value.Value
import nikolam.artcorner.common.main.ArtMain

interface ArtRoot {

    val routerState: Value<RouterState<*, Child>>

    sealed class Child {
        data class Main(val component: ArtMain) : Child()
    }
}
