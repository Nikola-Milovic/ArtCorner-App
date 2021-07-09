package nikolam.artcorner.common.create.integration

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.operator.map
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.badoo.reaktive.base.Consumer
import com.badoo.reaktive.base.invoke
import nikolam.artcorner.common.create.ArtCreate
import nikolam.artcorner.common.create.ArtCreate.Model
import nikolam.artcorner.common.create.ArtCreate.Output
import nikolam.artcorner.common.create.store.ArtCreateStoreProvider
import nikolam.artcorner.common.utils.asValue
import nikolam.artcorner.common.utils.getStore

class ArtCreateComponent(
    componentContext: ComponentContext,
    storeFactory: StoreFactory,
    private val output: Consumer<Output>
) : ArtCreate, ComponentContext by componentContext {

    private val store =
        instanceKeeper.getStore {
            ArtCreateStoreProvider(
                storeFactory = storeFactory
            ).provide()
        }

    override val models: Value<Model> = store.asValue().map(stateToModel)

    override fun closeCreation() {
        output(Output.Closed)
    }

    override fun finishCreation() {
        output(Output.Closed)
    }
}
