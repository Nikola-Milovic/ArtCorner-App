package nikolam.artcorner.common.create

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.Value
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.badoo.reaktive.base.Consumer
import nikolam.artcorner.common.create.integration.ArtCreateComponent
import nikolam.artcorner.common.data.GroupApi

interface ArtCreate {
    val models: Value<Model>

    fun closeCreation()
    fun finishCreation()

    data class Model(
        val text: String
    )

    sealed class Output {
        object Closed : Output()
        data class CreatedGroup(val gid : String) : Output()
    }

    interface Dependencies {
        val groupApi : GroupApi
        val userId : String
        val output : Consumer<Output>
        val storeFactory: StoreFactory
    }
}

@Suppress("FunctionName") // Factory function
fun ArtCreateFactory(componentContext: ComponentContext, dependencies: ArtCreate.Dependencies) : ArtCreate
= ArtCreateComponent(componentContext = componentContext, dependencies = dependencies)
