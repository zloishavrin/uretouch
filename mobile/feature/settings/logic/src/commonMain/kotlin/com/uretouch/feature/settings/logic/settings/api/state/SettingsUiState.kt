package com.uretouch.feature.settings.logic.settings.api.state

import com.uretouch.feature.settings.logic.settings.internal.fsm.state.SettingsState

class SettingsUiState

internal fun SettingsState.toUiState(): SettingsUiState {
    return when (this) {
        SettingsState.Initial -> SettingsUiState()

        is SettingsState.AsyncWorkerState.Logout -> sourceState.toUiState()
    }
}