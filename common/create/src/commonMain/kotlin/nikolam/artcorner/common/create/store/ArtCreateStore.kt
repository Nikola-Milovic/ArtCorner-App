package nikolam.artcorner.common.create.store

import com.arkivanov.mvikotlin.core.store.Store
import nikolam.artcorner.common.create.store.ArtCreateStore.Intent
import nikolam.artcorner.common.create.store.ArtCreateStore.State

internal interface ArtCreateStore : Store<Intent, State, Nothing> {

    sealed class Intent {}

    data class State(
        val text: String = ""
    )
}
