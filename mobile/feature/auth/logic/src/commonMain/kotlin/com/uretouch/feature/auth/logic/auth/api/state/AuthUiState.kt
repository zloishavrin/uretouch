package com.uretouch.feature.auth.logic.auth.api.state

import com.uretouch.feature.auth.logic.auth.internal.fsm.state.AuthState

data class AuthUiState(
    val email: String,
    val password: String,
    val error: String,
) {
    val enterButtonEnabled: Boolean = email.isNotBlank() && password.isNotBlank()
    val errorVisible: Boolean = error.isNotBlank()
}

internal fun AuthState.toUiState(): AuthUiState {
    return when (this) {
        is AuthState.Initial -> {
            AuthUiState(
                email = email,
                password = password,
                error = error
            )
        }

        is AuthState.AsyncWorkerState.StartLogin -> {
            AuthUiState(
                email = email,
                password = password,
                error = error
            )
        }

        is AuthState.NavigationState -> sourceState.toUiState()
    }
}