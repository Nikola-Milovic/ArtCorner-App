package nikolam.artcorner.common.details.store

import com.arkivanov.mvikotlin.core.store.*
import com.arkivanov.mvikotlin.extensions.reaktive.ReaktiveExecutor
import nikolam.artcorner.common.details.store.ArtDetailsStore.*

internal class ArtDetailsStoreProvider(
    private val storeFactory: StoreFactory,
    private val id: Long,
) {
    fun provide(): ArtDetailsStore =
        object : ArtDetailsStore, Store<Intent, State, Nothing> by storeFactory.create(
            name = "ArtDetailsStore",
            initialState = State(),
            bootstrapper = SimpleBootstrapper(Unit),
            executorFactory = ::ExecutorImpl,
            reducer = ReducerImpl
        ) {}

    private sealed class Result {}

    private inner class ExecutorImpl : ReaktiveExecutor<Intent, Unit, State, Result, Nothing>() {
        override fun executeIntent(intent: Intent, getState: () -> State): Unit =
            when (intent) {
                else -> {
                }
            }
    }

    private object ReducerImpl : Reducer<State, Result> {
        override fun State.reduce(result: Result): State =
            when (result) {
                else -> {
                    copy()
                }
            }
    }
}
