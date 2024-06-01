package com.uretouch.feature.auth.logic.auth.internal.fsm

import com.uretouch.feature.auth.logic.auth.internal.fsm.actions.BaseAuthAction
import com.uretouch.feature.auth.logic.auth.internal.fsm.state.AuthState
import ru.kontur.mobile.visualfsm.AsyncWorker
import ru.kontur.mobile.visualfsm.AsyncWorkerTask

internal class AuthAsyncWorker() : AsyncWorker<AuthState, BaseAuthAction>() {
    override fun onNextState(state: AuthState): AsyncWorkerTask<AuthState> {
        return when (state) {
            else -> AsyncWorkerTask.Cancel()
        }
    }
}