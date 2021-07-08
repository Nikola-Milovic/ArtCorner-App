package nikolam.artcorner.common.details.integration

import nikolam.artcorner.common.details.ArtDetails.*
import nikolam.artcorner.common.details.store.ArtDetailsStore.*

internal val stateToModel: (State) -> Model =
    {
        Model(
            text = it.text
        )
    }