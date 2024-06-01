package com.uretouch.feature.auth.logic.registration.internal.fsm.actions

import com.uretouch.feature.auth.logic.registration.internal.fsm.state.RegistrationState
import ru.kontur.mobile.visualfsm.Edge
import ru.kontur.mobile.visualfsm.SelfTransition

internal class RegistrationEmailChanged(
    private val email: String,
) : BaseRegistrationAction() {

    @Edge("EmailChange")
    inner class FromInitial : SelfTransition<RegistrationState.Initial>() {
        override fun transform(state: RegistrationState.Initial): RegistrationState.Initial {
            return state.copy(
                email = email,
                error = ""
            )
        }
    }
}