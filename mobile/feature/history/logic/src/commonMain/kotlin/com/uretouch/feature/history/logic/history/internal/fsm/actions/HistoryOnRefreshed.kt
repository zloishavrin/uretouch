package com.uretouch.feature.history.logic.history.internal.fsm.actions

import com.uretouch.feature.history.logic.history.internal.fsm.state.HistoryState
import ru.kontur.mobile.visualfsm.Edge
import ru.kontur.mobile.visualfsm.Transition

internal class HistoryOnRefreshed : BaseHistoryAction() {

    @Edge("OnRefreshed")
    inner class FromLoaded : Transition<HistoryState.Loaded, HistoryState.AsyncWorkerState.Loading>() {
        override fun transform(state: HistoryState.Loaded): HistoryState.AsyncWorkerState.Loading {
            return HistoryState.AsyncWorkerState.Loading(
                sourceState = state
            )
        }
    }

    @Edge("OnRefreshed")
    inner class FromError : Transition<HistoryState.Error, HistoryState.AsyncWorkerState.Loading>() {
        override fun transform(state: HistoryState.Error): HistoryState.AsyncWorkerState.Loading {
            return HistoryState.AsyncWorkerState.Loading(
                sourceState = state
            )
        }
    }
}