package nikolam.artcorner.common.create.integration

import nikolam.artcorner.common.create.ArtCreate.Model
import nikolam.artcorner.common.create.store.ArtCreateStore.State

internal val stateToModel: (State) -> Model =
    {
        Model(
            text = it.text
        )
    }