package com.uretouch.feature.auth.logic.auth.internal.fsm

import com.uretouch.common.core.extensions.runCatchingCancellable
import com.uretouch.domain.auth.interactor.AuthInteractor
import com.uretouch.feature.auth.logic.auth.internal.fsm.actions.AuthHandleLoginFail
import com.uretouch.feature.auth.logic.auth.internal.fsm.actions.AuthHandleLoginSuccess
import com.uretouch.feature.auth.logic.auth.internal.fsm.actions.BaseAuthAction
import com.uretouch.feature.auth.logic.auth.internal.fsm.state.AuthState
import ru.kontur.mobile.visualfsm.AsyncWorker
import ru.kontur.mobile.visualfsm.AsyncWorkerTask

internal class AuthAsyncWorker(
    private val authInteractor: AuthInteractor,
) : AsyncWorker<AuthState, BaseAuthAction>() {
    override fun onNextState(state: AuthState): AsyncWorkerTask<AuthState> {
        return when (state) {
            is AuthState.AsyncWorkerState.StartLogin -> {
                AsyncWorkerTask.ExecuteIfNotExist(state) {
                    runCatchingCancellable {
                        authInteractor.login(email = state.email, password = state.password)
                    }.onSuccess {
                        proceed(AuthHandleLoginSuccess(it))
                    }.onFailure {
                        proceed(AuthHandleLoginFail(it))
                    }
                }
            }

            is AuthState.NavigationState,
            is AuthState.Initial,
            -> AsyncWorkerTask.Cancel()
        }
    }
}