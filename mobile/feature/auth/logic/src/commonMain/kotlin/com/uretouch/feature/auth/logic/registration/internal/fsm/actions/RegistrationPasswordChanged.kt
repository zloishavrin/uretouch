package com.uretouch.feature.auth.logic.registration.internal.fsm.actions

import com.uretouch.feature.auth.logic.registration.internal.fsm.state.RegistrationState
import ru.kontur.mobile.visualfsm.Edge
import ru.kontur.mobile.visualfsm.SelfTransition

internal class RegistrationPasswordChanged(
    private val password: String,
) : BaseRegistrationAction() {

    @Edge("PasswordChange")
    inner class FromInitial : SelfTransition<RegistrationState.Initial>() {
        override fun transform(state: RegistrationState.Initial): RegistrationState.Initial {
            return state.copy(
                password = password,
                error = ""
            )
        }
    }
}