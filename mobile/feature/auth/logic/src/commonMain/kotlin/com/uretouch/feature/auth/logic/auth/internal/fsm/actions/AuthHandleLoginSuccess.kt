package com.uretouch.feature.auth.logic.auth.internal.fsm.actions

import com.uretouch.domain.auth.model.User
import com.uretouch.feature.auth.logic.auth.internal.fsm.state.AuthState
import ru.kontur.mobile.visualfsm.Edge
import ru.kontur.mobile.visualfsm.Transition

internal class AuthHandleLoginSuccess(
    private val user: User,
) : BaseAuthAction() {

    @Edge("LoginSuccess")
    inner class FromStartLoginToOpenCheckEmail : Transition<AuthState.AsyncWorkerState.StartLogin, AuthState.NavigationState.OpenCheckEmail>() {
        override fun predicate(state: AuthState.AsyncWorkerState.StartLogin): Boolean {
            return !user.isActivated
        }

        override fun transform(state: AuthState.AsyncWorkerState.StartLogin): AuthState.NavigationState.OpenCheckEmail {
            return AuthState.NavigationState.OpenCheckEmail(sourceState = state, user = user)
        }
    }

    @Edge("LoginSuccess")
    inner class FromStartLoginToOpenTab : Transition<AuthState.AsyncWorkerState.StartLogin, AuthState.NavigationState.OpenTab>() {
        override fun predicate(state: AuthState.AsyncWorkerState.StartLogin): Boolean {
            return user.isActivated
        }

        override fun transform(state: AuthState.AsyncWorkerState.StartLogin): AuthState.NavigationState.OpenTab {
            return AuthState.NavigationState.OpenTab(sourceState = state)
        }
    }
}