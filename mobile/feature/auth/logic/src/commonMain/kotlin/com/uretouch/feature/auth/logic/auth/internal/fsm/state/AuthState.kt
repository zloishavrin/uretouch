package com.uretouch.feature.auth.logic.auth.internal.fsm.state

import ru.kontur.mobile.visualfsm.State

internal sealed class AuthState : State {

    abstract val login: String
    abstract val password: String

    sealed class AsyncWorkerState : AuthState() {

    }

    data class Initial(
        override val login: String,
        override val password: String,
    ) : AuthState()

    companion object {
        fun initial(): AuthState {
            return Initial(login = "", password = "")
        }
    }
}