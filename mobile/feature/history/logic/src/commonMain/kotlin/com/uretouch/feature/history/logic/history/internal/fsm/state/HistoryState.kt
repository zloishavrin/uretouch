package com.uretouch.feature.history.logic.history.internal.fsm.state

import com.uretouch.domain.generations.model.Generation
import ru.kontur.mobile.visualfsm.State

internal sealed class HistoryState : State {

    sealed class AsyncWorkerState : HistoryState() {
        data class Loading(
            val sourceState: HistoryState?,
        ) : AsyncWorkerState()
    }

    data class Loaded(
        val generations: List<Generation>,
    ) : HistoryState()

    data object Error : HistoryState()

    companion object {
        fun initial(): HistoryState {
            return AsyncWorkerState.Loading(sourceState = null)
        }
    }
}