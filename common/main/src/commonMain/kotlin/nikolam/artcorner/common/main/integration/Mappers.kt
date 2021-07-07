package nikolam.artcorner.common.main.integration

import nikolam.artcorner.common.main.ArtMain.Model
import nikolam.artcorner.common.main.store.ArtMainStore.State

internal val stateToModel: (State) -> Model =
    {
        Model(
            items = it.items,
            text = it.text
        )
    }