package nikolam.artcorner.common.main.store

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.SimpleBootstrapper
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.reaktive.ReaktiveExecutor
import nikolam.artcorner.common.main.GroupItem
import nikolam.artcorner.common.main.store.ArtMainStore.Intent
import nikolam.artcorner.common.main.store.ArtMainStore.State

internal class ArtMainStoreProvider(
    private val storeFactory: StoreFactory,
) {
    fun provide(): ArtMainStore =
        object : ArtMainStore, Store<Intent, State, Nothing> by storeFactory.create(
            name = "ArtMainStore",
            initialState = State(),
            bootstrapper = SimpleBootstrapper(Unit),
            executorFactory = ::ExecutorImpl,
            reducer = ReducerImpl
        ) {}

    private sealed class Result {
        data class ItemsLoaded(val items: List<GroupItem>) : Result()
    }

    private inner class ExecutorImpl : ReaktiveExecutor<Intent, Unit, State, Result, Nothing>() {
        override fun executeAction(action: Unit, getState: () -> State) {
            dispatch(
                Result.ItemsLoaded(
                    items = listOf(
                        GroupItem("123456-123", "TestGroup1"),
                        GroupItem("123456-1234", "TestGroup2"),
                        GroupItem("123456-12345", "TestGroup3")
                    )
                )
            )
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
                is Result.ItemsLoaded -> copy(items = result.items)
            }
    }
}
