package com.uretouch.feature.settings.logic.settings.internal.fsm.actions

import com.uretouch.feature.settings.logic.settings.internal.fsm.state.SettingsState
import ru.kontur.mobile.visualfsm.Edge
import ru.kontur.mobile.visualfsm.Transition

internal class SettingsOnLogoutClicked() : BaseSettingsAction() {

    @Edge("OnLogoutClicked")
    inner class Blank : Transition<SettingsState, SettingsState.AsyncWorkerState.Logout>() {
        override fun transform(state: SettingsState): SettingsState.AsyncWorkerState.Logout {
            return SettingsState.AsyncWorkerState.Logout(
                sourceState = state
            )
        }
    }
}