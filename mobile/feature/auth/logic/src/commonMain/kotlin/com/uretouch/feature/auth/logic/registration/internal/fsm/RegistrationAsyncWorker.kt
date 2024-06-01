package com.uretouch.feature.auth.logic.registration.internal.fsm

import com.uretouch.common.core.extensions.runCatchingCancellable
import com.uretouch.domain.auth.interactor.AuthInteractor
import com.uretouch.feature.auth.logic.registration.internal.fsm.actions.BaseRegistrationAction
import com.uretouch.feature.auth.logic.registration.internal.fsm.actions.RegistrationHandleRegistryFail
import com.uretouch.feature.auth.logic.registration.internal.fsm.actions.RegistrationHandleRegistrySuccess
import com.uretouch.feature.auth.logic.registration.internal.fsm.state.RegistrationState
import ru.kontur.mobile.visualfsm.AsyncWorker
import ru.kontur.mobile.visualfsm.AsyncWorkerTask

internal class RegistrationAsyncWorker(
    private val authInteractor: AuthInteractor,
) : AsyncWorker<RegistrationState, BaseRegistrationAction>() {
    override fun onNextState(state: RegistrationState): AsyncWorkerTask<RegistrationState> {
        return when (state) {

            is RegistrationState.AsyncWorkerState.StartRegistry -> {
                AsyncWorkerTask.ExecuteIfNotExist(state) {
                    runCatchingCancellable {
                        authInteractor.registration(
                            email = state.sourceState.email,
                            password = state.sourceState.password
                        )
                    }.onSuccess {
                        proceed(RegistrationHandleRegistrySuccess(it))
                    }.onFailure {
                        proceed(RegistrationHandleRegistryFail(it))
                    }
                }

            }

            is RegistrationState.NavigationState,
            is RegistrationState.Initial,
            -> AsyncWorkerTask.Cancel()
        }
    }
}