package nikolam.artcorner.common.main.integration

import com.arkivanov.decompose.*
import com.arkivanov.decompose.statekeeper.Parcelable
import com.arkivanov.decompose.statekeeper.Parcelize
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.operator.map
import com.badoo.reaktive.base.Consumer
import nikolam.artcorner.common.create.ArtCreate
import nikolam.artcorner.common.create.ArtCreateFactory
import nikolam.artcorner.common.details.ArtDetails
import nikolam.artcorner.common.details.ArtDetailsFactory
import nikolam.artcorner.common.main.ArtMain
import nikolam.artcorner.common.main.ArtMain.Model
import nikolam.artcorner.common.main.store.ArtMainStoreProvider
import nikolam.artcorner.common.utils.Consumer
import nikolam.artcorner.common.utils.asValue
import nikolam.artcorner.common.utils.getStore

class ArtMainComponent(
    componentContext: ComponentContext,
    dependencies : ArtMain.Dependencies,
    private val artCreate: (ComponentContext, Consumer<ArtCreate.Output>) -> ArtCreate,
    private val artDetail: (ComponentContext, groupId: String, justCreated : Boolean, Consumer<ArtDetails.Output>) -> ArtDetails
) : ArtMain, ComponentContext by componentContext, ArtMain.Dependencies by dependencies {

    constructor(
        componentContext: ComponentContext,
        dependencies: ArtMain.Dependencies,
    ) : this(
        componentContext = componentContext,
        dependencies = dependencies,
        artCreate = { childContext, output ->
            artCreate(childContext, output, dependencies)
        },
        artDetail = { childContext, groupId, justCreated, output ->
            artDetail(childContext, groupId, justCreated, output, dependencies)
        }
    )

    private val store =
        instanceKeeper.getStore {
            ArtMainStoreProvider(
                storeFactory = storeFactory,
                preferences = preferences
            ).provide()
        }

    override val models: Value<Model> = store.asValue().map(stateToModel)

    private val router = router<Configuration, ArtMain.Child>(
        initialConfiguration = Configuration.Nothing,
        handleBackButton = true,
        childFactory = ::createChild
    )

    override val routerState: Value<RouterState<*, ArtMain.Child>> = router.state

    private fun createChild(
        configuration: Configuration,
        componentContext: ComponentContext
    ): ArtMain.Child =
        when (configuration) {
            is Configuration.Create -> ArtMain.Child.Create(
                artCreate(
                    componentContext,
                    Consumer(::onCreateOutput)
                )
            )
            is Configuration.Details -> ArtMain.Child.Details(
                artDetail(
                    componentContext,
                    configuration.gid,
                    configuration.justCreated,
                    Consumer(::onDetailsOutput)
                )
            )
            is Configuration.Nothing -> ArtMain.Child.Nothing
        }

    override fun navigateToCreateGroup() {
        router.push(Configuration.Create)
    }

    override fun navigateToDetailsGroup(gid: String) {
        router.push(Configuration.Details(gid, false))
    }

    private fun onCreateOutput(output: ArtCreate.Output): Unit =
        when (output) {
            is ArtCreate.Output.Closed -> router.pop()
            is ArtCreate.Output.CreatedGroup -> {
                router.replaceCurrent(Configuration.Details(output.gid,true))
            }
            else -> {}
        }

    private fun onDetailsOutput(output: ArtDetails.Output): Unit =
        when (output) {
            is ArtDetails.Output.Closed -> router.pop()
        }

    private sealed class Configuration : Parcelable {
        @Parcelize
        object Create : Configuration()

        @Parcelize
        data class Details(val gid: String, val justCreated : Boolean) : Configuration()

        @Parcelize
        object Nothing : Configuration()
    }
}

private fun artCreate(componentContext: ComponentContext, output : Consumer<ArtCreate.Output>,dependencies: ArtMain.Dependencies) : ArtCreate {
    return ArtCreateFactory(
        componentContext = componentContext,
        dependencies = object : ArtCreate.Dependencies, ArtMain.Dependencies by dependencies {
            override val userId: String = "test123"
            override val output: Consumer<ArtCreate.Output> = output
        }
    )
}

private fun artDetail(componentContext: ComponentContext, gid: String, justCreated: Boolean, output : Consumer<ArtDetails.Output>,dependencies: ArtMain.Dependencies) : ArtDetails {
    return ArtDetailsFactory(
        gid = gid,
        justCreated = justCreated,
        componentContext = componentContext,
        dependencies = object : ArtDetails.Dependencies, ArtMain.Dependencies by dependencies {
            override val userId: String = "test123"
            override val output: Consumer<ArtDetails.Output> = output
        }
    )
}

