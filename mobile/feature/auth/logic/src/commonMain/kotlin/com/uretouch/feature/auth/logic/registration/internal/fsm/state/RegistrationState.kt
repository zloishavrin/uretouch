package com.uretouch.feature.auth.logic.registration.internal.fsm.state

import com.uretouch.domain.auth.model.User
import ru.kontur.mobile.visualfsm.State

internal sealed class RegistrationState : State {

    data class Initial(
        val email: String,
        val password: String,
        val repeatPassword: String,
        val error: String,
    ) : RegistrationState()

    sealed class AsyncWorkerState : RegistrationState() {
        data class StartRegistry(
            val sourceState: Initial,
        ) : AsyncWorkerState()
    }

    sealed class NavigationState : RegistrationState() {
        data class ToCheckEmail(
            val sourceState: AsyncWorkerState.StartRegistry,
            val user: User,
        ) : NavigationState()
    }

    companion object {
        fun initial(email: String): RegistrationState {
            return Initial(
                email = email,
                password = "",
                repeatPassword = "",
                error = ""
            )
        }
    }
}