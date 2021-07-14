package nikolam.artcorner.common.main

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.RouterState
import com.arkivanov.decompose.value.Value
import com.arkivanov.mvikotlin.core.store.StoreFactory
import nikolam.artcorner.common.create.ArtCreate
import nikolam.artcorner.common.data.GroupApi
import nikolam.artcorner.common.details.ArtDetails
import nikolam.artcorner.common.main.integration.ArtMainComponent

interface ArtMain {

    val models: Value<Model>

    val routerState: Value<RouterState<*, Child>>

    fun navigateToCreateGroup()
    fun navigateToDetailsGroup(gid: String, justCreated: Boolean = false)

    data class Model(
        val items: List<GroupItem>,
        val text: String
    )

    sealed class Child {
        data class Create(val component: ArtCreate) : Child()
        data class Details(val component: ArtDetails) : Child()
        object Nothing : Child()
    }

    interface Dependencies{
        val groupApi : GroupApi
        val userId : String
        val storeFactory: StoreFactory
    }
}

@Suppress("FunctionName") // Factory function
fun ArtMainFactory(componentContext: ComponentContext, dependencies: ArtMain.Dependencies) : ArtMain =
    ArtMainComponent(componentContext = componentContext, dependencies = dependencies)

