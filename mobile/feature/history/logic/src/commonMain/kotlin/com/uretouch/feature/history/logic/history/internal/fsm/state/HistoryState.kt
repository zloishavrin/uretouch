package com.uretouch.feature.history.logic.history.internal.fsm.state

import ru.kontur.mobile.visualfsm.State

internal sealed class HistoryState : State {

    sealed class AsyncWorkerState : HistoryState() {
        data object Loading : AsyncWorkerState()
    }

    companion object {
        fun initial(): HistoryState {
            return AsyncWorkerState.Loading
        }
    }
}