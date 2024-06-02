package com.uretouch.feature.history.logic.history.internal.fsm

import com.uretouch.common.core.extensions.runCatchingCancellable
import com.uretouch.domain.generations.interactor.GenerationsInteractor
import com.uretouch.feature.history.logic.history.internal.fsm.actions.BaseHistoryAction
import com.uretouch.feature.history.logic.history.internal.fsm.actions.HistoryHandleLoadingFail
import com.uretouch.feature.history.logic.history.internal.fsm.actions.HistoryHandleLoadingSuccess
import com.uretouch.feature.history.logic.history.internal.fsm.state.HistoryState
import ru.kontur.mobile.visualfsm.AsyncWorker
import ru.kontur.mobile.visualfsm.AsyncWorkerTask

internal class HistoryAsyncWorker(
    private val generationsInteractor: GenerationsInteractor,
) : AsyncWorker<HistoryState, BaseHistoryAction>() {
    override fun onNextState(state: HistoryState): AsyncWorkerTask<HistoryState> {
        return when (state) {
            is HistoryState.AsyncWorkerState.Loading -> {
                AsyncWorkerTask.ExecuteIfNotExist(state) {
                    runCatchingCancellable {
                        generationsInteractor.getGenerationsHistory()
                    }.onSuccess {
                        proceed(HistoryHandleLoadingSuccess(it))
                    }.onFailure {
                        proceed(HistoryHandleLoadingFail())
                    }
                }
            }

            HistoryState.Error,
            is HistoryState.Loaded,
            -> AsyncWorkerTask.Cancel()
        }
    }
}