package com.uretouch.feature.auth.logic.auth.internal.fsm.actions

import com.uretouch.feature.auth.logic.auth.internal.fsm.state.AuthState
import ru.kontur.mobile.visualfsm.Edge
import ru.kontur.mobile.visualfsm.SelfTransition

internal class AuthLoginChanged(
    private val login: String,
) : BaseAuthAction() {

    @Edge("LoginChange")
    inner class FromInitial : SelfTransition<AuthState.Initial>() {
        override fun transform(state: AuthState.Initial): AuthState.Initial {
            return state.copy(login = login
            )
        }
    }

}