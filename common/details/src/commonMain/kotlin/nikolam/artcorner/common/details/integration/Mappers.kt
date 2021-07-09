package nikolam.artcorner.common.details.integration

import nikolam.artcorner.common.details.ArtDetails.Model
import nikolam.artcorner.common.details.store.ArtDetailsStore.State

internal val stateToModel: (State) -> Model =
    {
        Model(
            text = it.text
        )
    }