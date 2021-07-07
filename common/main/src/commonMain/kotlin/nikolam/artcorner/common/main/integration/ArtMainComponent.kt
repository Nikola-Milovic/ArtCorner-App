package nikolam.artcorner.common.main.integration

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.RouterState
import com.arkivanov.decompose.push
import com.arkivanov.decompose.router
import com.arkivanov.decompose.statekeeper.Parcelable
import com.arkivanov.decompose.statekeeper.Parcelize
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.operator.map
import com.arkivanov.mvikotlin.core.store.StoreFactory
import nikolam.artcorner.common.main.ArtMain
import nikolam.artcorner.common.main.store.ArtMainStoreProvider
import example.todo.common.utils.asValue
import example.todo.common.utils.getStore
import nikolam.artcorner.common.main.ArtMain.*

class ArtMainComponent(
    componentContext: ComponentContext,
    storeFactory: StoreFactory
) : ArtMain, ComponentContext by componentContext {

    private val store =
        instanceKeeper.getStore {
            ArtMainStoreProvider(
                storeFactory = storeFactory
            ).provide()
        }

    override val models: Value<Model> = store.asValue().map(stateToModel)

    private val router = router<Configuration, Child>(
            initialConfiguration = Configuration.Nothing,
            handleBackButton = true,
            childFactory = ::createChild
    )

    override val routerState: Value<RouterState<*, Child>> = router.state

    private fun createChild(configuration: Configuration, componentContext: ComponentContext): Child =
        when (configuration) {
            is Configuration.NewGroup -> Child.NewGroup
            is Configuration.GroupDetails ->  Child.GroupDetails
            is Configuration.Nothing ->  Child.Nothing
        }

    override fun navigateToNewGroup() {
        router.push(Configuration.NewGroup)
    }

    private sealed class Configuration : Parcelable{
        @Parcelize
        object NewGroup : Configuration()

        @Parcelize
        object GroupDetails : Configuration()

        @Parcelize
        object Nothing : Configuration()
    }
}
