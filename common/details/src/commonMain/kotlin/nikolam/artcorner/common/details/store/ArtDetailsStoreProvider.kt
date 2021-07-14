package nikolam.artcorner.common.details.store

import co.touchlab.kermit.Logger
import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.SimpleBootstrapper
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.reaktive.ReaktiveExecutor
import nikolam.artcorner.common.details.store.ArtDetailsStore.Intent
import nikolam.artcorner.common.details.store.ArtDetailsStore.State

internal class ArtDetailsStoreProvider(
    private val storeFactory: StoreFactory,
    private val gid: String,
    private val justCreated: Boolean,
) {
    fun provide(): ArtDetailsStore =
        object : ArtDetailsStore, Store<Intent, State, Nothing> by storeFactory.create(
            name = "ArtDetailsStore",
            initialState = State(gid = gid, justCreated = justCreated),
            bootstrapper = SimpleBootstrapper(Unit),
            executorFactory = ::ExecutorImpl,
            reducer = ReducerImpl
        ) {}

    private sealed class Result {
        object GroupJustCreated : Result()
    }

    private inner class ExecutorImpl : ReaktiveExecutor<Intent, Unit, State, Result, Nothing>() {
        override fun executeAction(action: Unit, getState: () -> State) {
            if (justCreated) {
                dispatch(Result.GroupJustCreated)
            }
        }

        override fun executeIntent(intent: Intent, getState: () -> State): Unit =
            when (intent) {
                else -> {
                }
            }
    }

    private object ReducerImpl : Reducer<State, Result> {
        override fun State.reduce(result: Result): State =
            when (result) {
                Result.GroupJustCreated -> {
                    copy(gid = gid, verified = false, ownerId = "123", userId = "123")
                }
                else -> {
                    copy()
                }
            }
    }
}
