package com.uretouch.feature.history.logic.history.internal.fsm

import com.uretouch.feature.history.logic.history.internal.fsm.actions.BaseHistoryAction
import com.uretouch.feature.history.logic.history.internal.fsm.state.HistoryState
import ru.kontur.mobile.visualfsm.AsyncWorker
import ru.kontur.mobile.visualfsm.AsyncWorkerTask

internal class HistoryAsyncWorker() : AsyncWorker<HistoryState, BaseHistoryAction>() {
    override fun onNextState(state: HistoryState): AsyncWorkerTask<HistoryState> {
        return when (state) {
            else -> AsyncWorkerTask.Cancel()
        }
    }
}