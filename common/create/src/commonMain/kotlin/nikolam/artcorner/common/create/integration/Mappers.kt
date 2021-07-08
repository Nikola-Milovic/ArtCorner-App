package nikolam.artcorner.common.create.integration

import nikolam.artcorner.common.create.ArtCreate.*
import nikolam.artcorner.common.create.store.ArtCreateStore.*

internal val stateToModel: (State) -> Model =
    {
        Model(
            text = it.text
        )
    }