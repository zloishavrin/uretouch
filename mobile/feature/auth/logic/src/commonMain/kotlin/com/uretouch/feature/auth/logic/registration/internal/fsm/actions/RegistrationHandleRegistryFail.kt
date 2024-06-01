package com.uretouch.feature.auth.logic.registration.internal.fsm.actions

import com.uretouch.feature.auth.logic.registration.internal.fsm.state.RegistrationState
import ru.kontur.mobile.visualfsm.Edge
import ru.kontur.mobile.visualfsm.Transition

internal class RegistrationHandleRegistryFail(
    private val error: Throwable,
) : BaseRegistrationAction() {

    @Edge("RegistryFail")
    inner class FromStartRegistry : Transition<RegistrationState.AsyncWorkerState.StartRegistry, RegistrationState.Initial>() {
        override fun transform(state: RegistrationState.AsyncWorkerState.StartRegistry): RegistrationState.Initial {
            return RegistrationState.Initial(
                email = state.sourceState.email,
                password = state.sourceState.password,
                repeatPassword = state.sourceState.repeatPassword,
                error = error.message ?: ""
            )
        }
    }
}