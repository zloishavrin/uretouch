package com.uretouch.feature.auth.logic.registration.internal.fsm.actions

import com.uretouch.feature.auth.logic.registration.internal.fsm.state.RegistrationState
import ru.kontur.mobile.visualfsm.Edge
import ru.kontur.mobile.visualfsm.SelfTransition
import ru.kontur.mobile.visualfsm.Transition

internal class RegistrationOnRegistryClicked() : BaseRegistrationAction() {

    @Edge("OnRegistryClicked")
    inner class FromInitialToStartRegistry : Transition<RegistrationState.Initial, RegistrationState.AsyncWorkerState.StartRegistry>() {
        override fun predicate(state: RegistrationState.Initial): Boolean {
            return state.password == state.repeatPassword
        }

        override fun transform(state: RegistrationState.Initial): RegistrationState.AsyncWorkerState.StartRegistry {
            return RegistrationState.AsyncWorkerState.StartRegistry(
                sourceState = state
            )
        }
    }

    @Edge("OnRegistryClicked")
    inner class FromInitial : SelfTransition<RegistrationState.Initial>() {
        override fun predicate(state: RegistrationState.Initial): Boolean {
            return state.password != state.repeatPassword
        }

        override fun transform(state: RegistrationState.Initial): RegistrationState.Initial {
            return state.copy(error = "Пароли не совпадают")
        }
    }
}