package nikolam.artcorner.common.main.store

import com.arkivanov.mvikotlin.core.store.Store
import nikolam.artcorner.common.main.GroupItem
import nikolam.artcorner.common.main.store.ArtMainStore.Intent
import nikolam.artcorner.common.main.store.ArtMainStore.State

internal interface ArtMainStore : Store<Intent, State, Nothing> {

    sealed class Intent {
    }

    data class State(
        val items: List<GroupItem> = emptyList(),
        val userId: String = ""
    )
}
