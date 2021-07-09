package nikolam.artcorner.common.details.integration

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.operator.map
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.badoo.reaktive.base.Consumer
import com.badoo.reaktive.base.invoke
import nikolam.artcorner.common.details.ArtDetails
import nikolam.artcorner.common.details.ArtDetails.Model
import nikolam.artcorner.common.details.ArtDetails.Output
import nikolam.artcorner.common.details.store.ArtDetailsStoreProvider
import nikolam.artcorner.common.utils.asValue
import nikolam.artcorner.common.utils.getStore

class ArtDetailsComponent(
    componentContext: ComponentContext,
    storeFactory: StoreFactory,
    private val output: Consumer<Output>,
    private val id: Long
) : ArtDetails, ComponentContext by componentContext {

    private val store =
        instanceKeeper.getStore {
            ArtDetailsStoreProvider(
                storeFactory = storeFactory,
                id = id
            ).provide()
        }

    override val models: Value<Model> = store.asValue().map(stateToModel)

    override fun closeDetails() {
        output(Output.Closed)
    }
}
