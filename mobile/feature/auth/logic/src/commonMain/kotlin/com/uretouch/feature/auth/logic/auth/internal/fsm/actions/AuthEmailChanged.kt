package com.uretouch.feature.auth.logic.auth.internal.fsm.actions

import com.uretouch.feature.auth.logic.auth.internal.fsm.state.AuthState
import ru.kontur.mobile.visualfsm.Edge
import ru.kontur.mobile.visualfsm.SelfTransition

internal class AuthEmailChanged(
    private val email: String,
) : BaseAuthAction() {

    @Edge("EmailChange")
    inner class FromInitial : SelfTransition<AuthState.Initial>() {
        override fun transform(state: AuthState.Initial): AuthState.Initial {
            return state.copy(
                email = email,
                error = ""
            )
        }
    }

}