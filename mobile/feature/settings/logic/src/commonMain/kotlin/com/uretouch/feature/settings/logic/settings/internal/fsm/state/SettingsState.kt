package com.uretouch.feature.settings.logic.settings.internal.fsm.state

import ru.kontur.mobile.visualfsm.State

internal sealed class SettingsState : State {

    sealed class AsyncWorkerState : SettingsState() {
        data object Loading : AsyncWorkerState()
        data class Logout(
            val sourceState: SettingsState,
        ) : AsyncWorkerState()
    }

    companion object {
        fun initial(): SettingsState {
            return AsyncWorkerState.Loading
        }
    }
}