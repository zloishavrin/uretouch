package com.uretouch.feature.auth.logic.auth.internal.fsm.actions

import com.uretouch.feature.auth.logic.auth.internal.fsm.state.AuthState
import ru.kontur.mobile.visualfsm.Edge
import ru.kontur.mobile.visualfsm.SelfTransition

internal class AuthPasswordChanged(
    private val password: String,
) : BaseAuthAction() {

    @Edge("PasswordChange")
    inner class FromInitial : SelfTransition<AuthState.Initial>() {
        override fun transform(state: AuthState.Initial): AuthState.Initial {
            return state.copy(
                password = password,
                error = ""
            )
        }
    }
}