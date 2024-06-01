package com.uretouch.feature.auth.logic.registration.internal.fsm.actions

import com.uretouch.domain.auth.model.User
import com.uretouch.feature.auth.logic.registration.internal.fsm.state.RegistrationState
import ru.kontur.mobile.visualfsm.Edge
import ru.kontur.mobile.visualfsm.Transition

internal class RegistrationHandleRegistrySuccess(
    private val user: User,
) : BaseRegistrationAction() {

    @Edge("RegistrySuccess")
    inner class FromStartRegistry : Transition<RegistrationState.AsyncWorkerState.StartRegistry, RegistrationState.NavigationState.ToCheckEmail>() {
        override fun transform(state: RegistrationState.AsyncWorkerState.StartRegistry): RegistrationState.NavigationState.ToCheckEmail {
            return RegistrationState.NavigationState.ToCheckEmail(
                sourceState = state,
                user = user
            )
        }
    }
}