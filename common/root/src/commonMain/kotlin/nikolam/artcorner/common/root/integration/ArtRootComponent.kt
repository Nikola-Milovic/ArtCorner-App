package nikolam.artcorner.common.root.integration

import co.touchlab.stately.ensureNeverFrozen
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.RouterState
import com.arkivanov.decompose.childContext
import com.arkivanov.decompose.router
import com.arkivanov.decompose.statekeeper.Parcelable
import com.arkivanov.decompose.statekeeper.Parcelize
import com.arkivanov.decompose.value.Value
import nikolam.artcorner.common.main.ArtMain
import nikolam.artcorner.common.main.ArtMainFactory
import nikolam.artcorner.common.root.ArtRoot
import nikolam.artcorner.common.root.ArtRoot.Child

class ArtRootComponent internal constructor(
    componentContext: ComponentContext,
    private val artMain: (ComponentContext) -> ArtMain,
    private val artAuth: (ComponentContext) -> ArtMain,
) : ArtRoot, ComponentContext by componentContext {

    constructor(
        componentContext: ComponentContext,
        dependencies: ArtRoot.Dependencies,
    ) : this(
        componentContext = componentContext,
        artMain = { childContext ->
            artMain(componentContext = childContext, dependencies = dependencies)
        },
        artAuth = {childContext ->
            artAuth(componentContext = childContext, dependencies = dependencies)
        }
    ) {
        instanceKeeper.ensureNeverFrozen()
    }

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
            is Configuration.Auth -> Child.Auth(artAuth(componentContext))
        }

    private sealed class Configuration : Parcelable {
        @Parcelize
        object Main : Configuration()

        @Parcelize
        object Auth : Configuration()
    }
}

private fun artMain(
    componentContext: ComponentContext,
    dependencies: ArtRoot.Dependencies
): ArtMain {
    return ArtMainFactory(
        componentContext = componentContext,
        dependencies = object : ArtMain.Dependencies, ArtRoot.Dependencies by dependencies {}
    )
}

private fun artAuth(
    componentContext: ComponentContext,
    dependencies: ArtRoot.Dependencies
): ArtMain {
    return ArtMainFactory(
        componentContext = componentContext,
        dependencies = object : ArtMain.Dependencies, ArtRoot.Dependencies by dependencies {}
    )
}