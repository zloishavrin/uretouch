package com.uretouch.feature.auth.logic.auth.api.state

import com.uretouch.feature.auth.logic.auth.internal.fsm.state.AuthState

data class AuthUiState(
    val login: String,
    val password: String,
)

internal fun AuthState.toUiState(): AuthUiState {
    return when (this) {
        is AuthState.Initial -> AuthUiState(login = login, password = password)
    }
}