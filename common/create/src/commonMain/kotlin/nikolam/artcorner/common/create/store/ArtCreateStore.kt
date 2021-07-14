package nikolam.artcorner.common.create.store

import com.arkivanov.mvikotlin.core.store.Store
import nikolam.artcorner.common.create.ArtCreate
import nikolam.artcorner.common.create.store.ArtCreateStore.*

internal interface ArtCreateStore : Store<Intent, State, Label> {

    sealed class Intent {
        data class CreateGroup(val maxMembers: Int) : Intent()
    }

    sealed class Label {
        data class CreatedGroup(val gid : String) : Label()
    }

    data class State(
        val text: String = ""
    )
}
