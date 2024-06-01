package com.uretouch.feature.auth.logic.auth.internal.fsm.actions

import com.uretouch.feature.auth.logic.auth.internal.fsm.state.AuthState
import ru.kontur.mobile.visualfsm.Edge
import ru.kontur.mobile.visualfsm.Transition

internal class AuthOnLoginClicked : BaseAuthAction() {

    @Edge("OnLoginClicked")
    inner class FromInitial : Transition<AuthState.Initial, AuthState.AsyncWorkerState.StartLogin>() {
        override fun transform(state: AuthState.Initial): AuthState.AsyncWorkerState.StartLogin {
            return AuthState.AsyncWorkerState.StartLogin(
                email = state.email,
                password = state.password
            )
        }
    }
}