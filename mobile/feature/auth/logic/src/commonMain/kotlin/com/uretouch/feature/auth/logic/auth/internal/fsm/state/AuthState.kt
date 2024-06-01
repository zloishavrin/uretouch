package com.uretouch.feature.auth.logic.auth.internal.fsm.state

import com.uretouch.domain.auth.model.User
import ru.kontur.mobile.visualfsm.State

internal sealed class AuthState : State {

    abstract val email: String
    abstract val password: String
    abstract val error: String

    sealed class AsyncWorkerState : AuthState() {
        data class StartLogin(
            override val email: String,
            override val password: String,
        ) : AsyncWorkerState() {
            override val error: String = ""
        }
    }

    sealed class NavigationState : AuthState() {
        abstract val sourceState: AsyncWorkerState.StartLogin

        data class OpenTab(
            override val sourceState: AsyncWorkerState.StartLogin,
        ) : NavigationState() {
            override val email: String = sourceState.email
            override val password: String = sourceState.password
            override val error: String = sourceState.error
        }

        data class OpenCheckEmail(
            override val sourceState: AsyncWorkerState.StartLogin,
            val user: User,
        ) : NavigationState() {
            override val email: String = sourceState.email
            override val password: String = sourceState.password
            override val error: String = sourceState.error
        }
    }

    data class Initial(
        override val email: String,
        override val password: String,
        override val error: String,
    ) : AuthState()

    companion object {
        fun initial(): AuthState {
            return Initial(email = "", password = "", error = "")
        }
    }
}