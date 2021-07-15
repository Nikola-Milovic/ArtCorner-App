package nikolam.artcorner.common.root

import com.arkivanov.decompose.RouterState
import com.arkivanov.decompose.value.Value
import com.arkivanov.mvikotlin.core.store.StoreFactory
import nikolam.artcorner.common.data.GroupApi
import nikolam.artcorner.common.main.ArtMain
import nikolam.artcorner.common.di.preference.PreferenceManager

interface ArtRoot {

    val routerState: Value<RouterState<*, Child>>

    sealed class Child {
        data class Main(val component: ArtMain) : Child()
    }

    interface Dependencies {
        val groupApi: GroupApi
        val preferences : PreferenceManager
        val storeFactory: StoreFactory
    }
}
