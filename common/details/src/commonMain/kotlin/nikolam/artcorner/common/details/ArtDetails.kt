package nikolam.artcorner.common.details

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.Value
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.badoo.reaktive.base.Consumer
import nikolam.artcorner.common.data.GroupApi
import nikolam.artcorner.common.details.integration.ArtDetailsComponent

interface ArtDetails {

    val models: Value<Model>

    fun closeDetails()

    data class Model(
       val isOwner : Boolean,
       val isVerified : Boolean,
       val gid : String
    )

    sealed class Output {
        object Closed : Output()
    }

    interface Dependencies {
        val groupApi : GroupApi
        val userId : String
        val output : Consumer<Output>
        val storeFactory: StoreFactory
    }
}

@Suppress("FunctionName") // Factory function
fun ArtDetailsFactory(
    componentContext: ComponentContext,
    dependencies: ArtDetails.Dependencies,
    gid: String,
    justCreated: Boolean
) : ArtDetails
        = ArtDetailsComponent(componentContext = componentContext, dependencies = dependencies, gid = gid, justCreated = justCreated)

