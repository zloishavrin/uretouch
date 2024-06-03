package com.uretouch.feature.history.logic.history.internal.fsm.actions

import com.uretouch.domain.generations.model.Generation
import com.uretouch.feature.history.logic.history.internal.fsm.state.HistoryState
import ru.kontur.mobile.visualfsm.Edge
import ru.kontur.mobile.visualfsm.Transition

internal class HistoryHandleLoadingSuccess(
    private val generations: List<Generation>,
) : BaseHistoryAction() {

    @Edge("LoadingSuccess")
    inner class Blank : Transition<HistoryState.AsyncWorkerState.Loading, HistoryState.Loaded>() {
        override fun transform(state: HistoryState.AsyncWorkerState.Loading): HistoryState.Loaded {
            return HistoryState.Loaded(
                generations = generations
            )
        }
    }

    @Edge("LoadingSuccess")
    inner class FromLoaded : Transition<HistoryState.Loaded, HistoryState.Loaded>() {
        override fun transform(state: HistoryState.Loaded): HistoryState.Loaded {
            return HistoryState.Loaded(
                generations = generations
            )
        }
    }
}