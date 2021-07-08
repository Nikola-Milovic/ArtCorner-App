package nikolam.artcorner.common.root.integration

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.RouterState
import com.arkivanov.decompose.router
import com.arkivanov.decompose.statekeeper.Parcelable
import com.arkivanov.decompose.statekeeper.Parcelize
import com.arkivanov.decompose.value.Value
import com.arkivanov.mvikotlin.core.store.StoreFactory
import nikolam.artcorner.common.main.ArtMain
import nikolam.artcorner.common.main.integration.ArtMainComponent
import nikolam.artcorner.common.root.ArtRoot
import nikolam.artcorner.common.root.ArtRoot.*

class ArtRootComponent internal constructor(
    componentContext: ComponentContext,
    private val artMain: (ComponentContext) -> ArtMain,
) : ArtRoot, ComponentContext by componentContext {

    constructor(
        componentContext: ComponentContext,
        storeFactory: StoreFactory,
    ) : this(
        componentContext = componentContext,
        artMain = { childContext ->
            ArtMainComponent(
                componentContext = childContext,
                storeFactory = storeFactory
            )
        }
    )

    private val router =
        router<Configuration, Child>(
            initialConfiguration = Configuration.Main,
            handleBackButton = true,
            childFactory = ::createChild
        )

    override val routerState: Value<RouterState<*, Child>> = router.state

    private fun createChild(
        configuration: Configuration,
        componentContext: ComponentContext
    ): Child =
        when (configuration) {
            is Configuration.Main -> Child.Main(artMain(componentContext))
        }

    private sealed class Configuration : Parcelable {
        @Parcelize
        object Main : Configuration()
    }
}
