package nikolam.artcorner.common.create.store

import co.touchlab.kermit.Kermit
import com.arkivanov.decompose.value.Value
import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.SimpleBootstrapper
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.reaktive.ReaktiveExecutor
import com.badoo.reaktive.scheduler.ioScheduler
import com.badoo.reaktive.scheduler.mainScheduler
import com.badoo.reaktive.single.map
import com.badoo.reaktive.single.observeOn
import com.badoo.reaktive.single.subscribe
import com.badoo.reaktive.single.subscribeOn
import nikolam.artcorner.common.create.store.ArtCreateStore.*
import nikolam.artcorner.common.data.GroupApi

internal class ArtCreateStoreProvider(
    private val storeFactory: StoreFactory,
    private val groupApi : GroupApi
) {
    fun provide(): ArtCreateStore =
        object : ArtCreateStore, Store<Intent, State, Label> by storeFactory.create(
            name = "ArtCreateStore",
            initialState = State(),
            bootstrapper = SimpleBootstrapper(Unit),
            executorFactory = ::ExecutorImpl,
            reducer = ReducerImpl
        ) {}

    private sealed class Result {}

    private inner class ExecutorImpl : ReaktiveExecutor<Intent, Unit, State, Result, Label>() {
        override fun executeIntent(intent: Intent, getState: () -> State): Unit =
            when (intent) {
                is Intent.CreateGroup -> {
                    groupApi.createGroup().subscribeOn(ioScheduler)
                        .observeOn(mainScheduler)
                        .subscribeScoped {
                            if (it.status == 200) {
                                publish(Label.CreatedGroup(it.gid))
                            }
                        }
                    Unit
                }
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
