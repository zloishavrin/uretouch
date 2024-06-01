package com.uretouch.feature.auth.logic.auth.internal.fsm.actions

import com.uretouch.feature.auth.logic.auth.internal.fsm.state.AuthState
import ru.kontur.mobile.visualfsm.Edge
import ru.kontur.mobile.visualfsm.Transition

internal class AuthHandleLoginFail(
    private val error: Throwable,
) : BaseAuthAction() {

    @Edge("LoginFail")
    inner class FromStartLoginToInitial : Transition<AuthState.AsyncWorkerState.StartLogin, AuthState.Initial>() {

        override fun transform(state: AuthState.AsyncWorkerState.StartLogin): AuthState.Initial {
            return AuthState.Initial(
                email = state.email,
                password = state.password,
                error = error.message ?: ""
            )
        }
    }
}