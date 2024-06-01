package com.uretouch.feature.auth.logic.registration.api.state

import com.uretouch.feature.auth.logic.registration.internal.fsm.state.RegistrationState

data class RegistrationUiState(
    val email: String,
    val password: String,
    val repeatPassword: String,
    val isLoading: Boolean,
    val error: String,
) {
    val errorVisible: Boolean = error.isNotBlank()
    val registryEnabled: Boolean = email.isNotBlank() && repeatPassword.isNotBlank() && password.isNotBlank()
}

internal fun RegistrationState.toUiState(): RegistrationUiState {
    return when (this) {
        is RegistrationState.Initial -> {
            RegistrationUiState(
                email = email,
                password = password,
                repeatPassword = repeatPassword,
                isLoading = false,
                error = error
            )
        }

        is RegistrationState.AsyncWorkerState.StartRegistry -> {
            RegistrationUiState(
                email = sourceState.email,
                password = sourceState.password,
                repeatPassword = sourceState.repeatPassword,
                isLoading = true,
                error = sourceState.error
            )
        }

        is RegistrationState.NavigationState.ToCheckEmail -> sourceState.toUiState()
    }
}