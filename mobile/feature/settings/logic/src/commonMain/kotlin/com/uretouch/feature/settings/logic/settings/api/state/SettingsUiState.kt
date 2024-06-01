package com.uretouch.feature.settings.logic.settings.api.state

import com.uretouch.feature.settings.logic.settings.internal.fsm.state.SettingsState

data class SettingsUiState(
    val email: String,
    val isLoading: Boolean,
)

internal fun SettingsState.toUiState(): SettingsUiState {
    return when (this) {
        SettingsState.AsyncWorkerState.Loading -> {
            SettingsUiState(
                email = "",
                isLoading = true
            )
        }

        is SettingsState.AsyncWorkerState.Logout -> sourceState.toUiState()
    }
}