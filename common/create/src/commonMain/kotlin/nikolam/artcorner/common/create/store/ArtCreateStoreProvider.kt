package nikolam.artcorner.common.create.store

import com.arkivanov.mvikotlin.core.store.*
import com.arkivanov.mvikotlin.extensions.reaktive.ReaktiveExecutor
import nikolam.artcorner.common.create.store.ArtCreateStore.*

internal class ArtCreateStoreProvider(
    private val storeFactory: StoreFactory,
) {
    fun provide(): ArtCreateStore =
        object : ArtCreateStore, Store<Intent, State, Nothing> by storeFactory.create(
            name = "ArtCreateStore",
            initialState = State(),
            bootstrapper = SimpleBootstrapper(Unit),
            executorFactory = ::ExecutorImpl,
            reducer = ReducerImpl
        ) {}

    private sealed class Result {}

    private inner class ExecutorImpl : ReaktiveExecutor<Intent, Unit, State, Result, Nothing>() {
        override fun executeIntent(intent: Intent, getState: () -> State): Unit =
            when (intent) {
                else -> {}
            }
    }

    private object ReducerImpl : Reducer<State, Result> {
        override fun State.reduce(result: Result): State =
            when (result) {
                else -> {copy()}
            }
    }
}
