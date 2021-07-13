package nikolam.artcorner.common.details.store

import com.arkivanov.mvikotlin.core.store.Store
import nikolam.artcorner.common.details.store.ArtDetailsStore.Intent
import nikolam.artcorner.common.details.store.ArtDetailsStore.State

internal interface ArtDetailsStore : Store<Intent, State, Nothing> {

    sealed class Intent {}

    data class State(
        val gid: String = "",
        val verified : Boolean = false,
        val ownerId : String = "",
        val userId : String = ""
    )
}
