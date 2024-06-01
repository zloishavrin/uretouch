package com.uretouch.feature.settings.logic.settings.internal.fsm

import com.uretouch.feature.settings.logic.settings.internal.fsm.actions.BaseSettingsAction
import com.uretouch.feature.settings.logic.settings.internal.fsm.state.SettingsState
import ru.kontur.mobile.visualfsm.AsyncWorker
import ru.kontur.mobile.visualfsm.AsyncWorkerTask

internal class SettingsAsyncWorker() : AsyncWorker<SettingsState, BaseSettingsAction>() {
    override fun onNextState(state: SettingsState): AsyncWorkerTask<SettingsState> {
        return when (state) {
            else -> AsyncWorkerTask.Cancel()
        }
    }
}