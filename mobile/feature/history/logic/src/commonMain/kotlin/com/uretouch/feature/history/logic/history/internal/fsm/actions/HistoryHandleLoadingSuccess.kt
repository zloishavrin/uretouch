package com.uretouch.feature.history.logic.history.internal.fsm.actions

import com.uretouch.feature.history.logic.history.internal.fsm.state.HistoryState
import ru.kontur.mobile.visualfsm.Edge
import ru.kontur.mobile.visualfsm.Transition

internal class HistoryHandleLoadingSuccess() : BaseHistoryAction() {

    @Edge("LoadingSuccess")
    inner class Blank : Transition<HistoryState.AsyncWorkerState.Loading, HistoryState.AsyncWorkerState.Loading>() {
        override fun transform(state: HistoryState.AsyncWorkerState.Loading): HistoryState.AsyncWorkerState.Loading {
            return HistoryState.AsyncWorkerState.Loading
        }
    }
}