package com.uretouch.feature.history.logic.history.internal.fsm.actions

import com.uretouch.feature.history.logic.history.internal.fsm.state.HistoryState
import ru.kontur.mobile.visualfsm.Edge
import ru.kontur.mobile.visualfsm.Transition

internal class HistoryHandleLoadingFail : BaseHistoryAction() {

    @Edge("LoadingFail")
    inner class Blank : Transition<HistoryState.AsyncWorkerState.Loading, HistoryState.Error>() {
        override fun transform(state: HistoryState.AsyncWorkerState.Loading): HistoryState.Error {
            return HistoryState.Error
        }
    }
}