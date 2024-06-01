package com.uretouch.feature.auth.logic.auth.internal.fsm.actions

import com.uretouch.feature.auth.logic.auth.internal.fsm.state.AuthState
import ru.kontur.mobile.visualfsm.Edge
import ru.kontur.mobile.visualfsm.Transition

internal class AuthNavigationCompleted : BaseAuthAction() {

    @Edge("NavigationCompleted")
    inner class ToInitial : Transition<AuthState.NavigationState, AuthState.Initial>() {
        override fun transform(state: AuthState.NavigationState): AuthState.Initial {
            return AuthState.Initial(
                email = state.email,
                password = state.password,
                error = state.error
            )
        }
    }
}