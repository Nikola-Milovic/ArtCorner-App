package nikolam.artcorner.common.main.integration

import com.arkivanov.decompose.*
import com.arkivanov.decompose.push
import com.arkivanov.decompose.statekeeper.Parcelable
import com.arkivanov.decompose.statekeeper.Parcelize
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.operator.map
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.badoo.reaktive.base.Consumer
import nikolam.artcorner.common.create.ArtCreate
import nikolam.artcorner.common.create.integration.ArtCreateComponent
import nikolam.artcorner.common.details.ArtDetails
import nikolam.artcorner.common.details.integration.ArtDetailsComponent
import nikolam.artcorner.common.main.ArtMain
import nikolam.artcorner.common.main.store.ArtMainStoreProvider
import nikolam.artcorner.common.utils.asValue
import nikolam.artcorner.common.utils.getStore
import nikolam.artcorner.common.main.ArtMain.*
import nikolam.artcorner.common.utils.Consumer

class ArtMainComponent(
    componentContext: ComponentContext,
    storeFactory: StoreFactory,
    private val artCreate: (ComponentContext, Consumer<ArtCreate.Output>) -> ArtCreate,
    private val artDetail: (ComponentContext, groupId: Long, Consumer<ArtDetails.Output>) -> ArtDetails
) : ArtMain, ComponentContext by componentContext {

    constructor(
        componentContext: ComponentContext,
        storeFactory: StoreFactory,
    ) : this(
        componentContext = componentContext,
        storeFactory = storeFactory,
        artCreate = { childContext, output ->
            ArtCreateComponent(
                componentContext = childContext,
                storeFactory = storeFactory,
                output = output
            )
        },
        artDetail = { childContext, groupId, output ->
            ArtDetailsComponent(
                componentContext = childContext,
                storeFactory = storeFactory,
                output = output,
                id = groupId
            )
        }
    )


    private val store =
        instanceKeeper.getStore {
            ArtMainStoreProvider(
                storeFactory = storeFactory
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
                    configuration.id,
                    Consumer(::onDetailsOutput)
                )
            )
            is Configuration.Nothing -> ArtMain.Child.Nothing
        }

    override fun navigateToCreateGroup() {
        router.push(Configuration.Create)
    }

    override fun navigateToDetailsGroup(id: Long) {
        router.push(Configuration.Details(id))
    }

    private fun onCreateOutput(output: ArtCreate.Output): Unit =
        when (output) {
            is ArtCreate.Output.Closed -> router.pop()
        }

    private fun onDetailsOutput(output: ArtDetails.Output): Unit =
        when (output) {
            is ArtDetails.Output.Closed -> router.pop()
        }

    private sealed class Configuration : Parcelable {
        @Parcelize
        object Create : Configuration()

        @Parcelize
        data class Details(val id: Long) : Configuration()

        @Parcelize
        object Nothing : Configuration()
    }
}
