package com.uretouch.feature.settings.logic.settings.internal.fsm

import com.uretouch.common.core.eventDispatcher.AuthEventDispatcher
import com.uretouch.common.core.extensions.runCatchingCancellable
import com.uretouch.domain.auth.interactor.AuthInteractor
import com.uretouch.feature.settings.logic.settings.internal.fsm.actions.BaseSettingsAction
import com.uretouch.feature.settings.logic.settings.internal.fsm.state.SettingsState
import ru.kontur.mobile.visualfsm.AsyncWorker
import ru.kontur.mobile.visualfsm.AsyncWorkerTask

internal class SettingsAsyncWorker(
    private val authInteractor: AuthInteractor,
    private val authEventDispatcher: AuthEventDispatcher,
) : AsyncWorker<SettingsState, BaseSettingsAction>() {
    override fun onNextState(state: SettingsState): AsyncWorkerTask<SettingsState> {
        return when (state) {
            is SettingsState.AsyncWorkerState.Logout -> {
                AsyncWorkerTask.ExecuteIfNotExist(state) {
                    runCatchingCancellable {
                        authInteractor.logout()
                    }.onSuccess {
                        authEventDispatcher.logout()
                    }.onFailure {
                        authEventDispatcher.logout()
                    }
                }
            }

            SettingsState.Initial -> AsyncWorkerTask.Cancel()
        }
    }
}