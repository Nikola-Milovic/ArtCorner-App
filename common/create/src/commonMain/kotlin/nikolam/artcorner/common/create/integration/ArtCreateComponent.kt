package nikolam.artcorner.common.create.integration

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.lifecycle.doOnStart
import com.arkivanov.decompose.lifecycle.doOnStop
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.operator.map
import com.arkivanov.mvikotlin.core.binder.Binder
import com.arkivanov.mvikotlin.extensions.reaktive.bind
import com.arkivanov.mvikotlin.extensions.reaktive.labels
import com.badoo.reaktive.base.invoke
import nikolam.artcorner.common.create.ArtCreate
import nikolam.artcorner.common.create.ArtCreate.Model
import nikolam.artcorner.common.create.ArtCreate.Output
import nikolam.artcorner.common.create.store.ArtCreateStore
import nikolam.artcorner.common.create.store.ArtCreateStoreProvider
import nikolam.artcorner.common.utils.asValue
import nikolam.artcorner.common.utils.getStore

class ArtCreateComponent(
    componentContext: ComponentContext,
    dependencies: ArtCreate.Dependencies
) : ArtCreate, ComponentContext by componentContext, ArtCreate.Dependencies by dependencies {

    private var binder: Binder? = null

    private val store : ArtCreateStore

    init {
        lifecycle.doOnStart {onStart() }
        lifecycle.doOnStop { onStop() }

        store =
            instanceKeeper.getStore {
                ArtCreateStoreProvider(
                    storeFactory = storeFactory,
                    groupApi = groupApi
                ).provide()
            }

        binder =
            bind {
                store.labels.bindTo {
                    when (it) {
                        is ArtCreateStore.Label.CreatedGroup -> {
                            output(Output.CreatedGroup(it.gid))
                        }
                    }
                }
            }
    }

    override val models: Value<Model> = store.asValue().map(stateToModel)

    override fun closeCreation() {
        output(Output.Closed)
    }

    override fun finishCreation(maxMembers : Int) {
        store.accept(ArtCreateStore.Intent.CreateGroup(maxMembers))
    }

    private fun onStart() {
        binder?.start()
    }

    private fun onStop() {
        binder?.stop()
    }
}
