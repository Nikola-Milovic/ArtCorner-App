package nikolam.artcorner.common.create.integration

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.operator.map
import com.badoo.reaktive.base.invoke
import nikolam.artcorner.common.create.ArtCreate
import nikolam.artcorner.common.create.ArtCreate.Model
import nikolam.artcorner.common.create.ArtCreate.Output
import nikolam.artcorner.common.create.store.ArtCreateStoreProvider
import nikolam.artcorner.common.utils.asValue
import nikolam.artcorner.common.utils.getStore

class ArtCreateComponent(
    componentContext: ComponentContext,
    dependencies: ArtCreate.Dependencies
) : ArtCreate, ComponentContext by componentContext, ArtCreate.Dependencies by dependencies {

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
