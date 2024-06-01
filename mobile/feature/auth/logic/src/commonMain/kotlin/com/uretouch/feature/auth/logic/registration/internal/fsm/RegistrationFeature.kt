package com.uretouch.feature.auth.logic.registration.internal.fsm

import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import com.uretouch.feature.auth.logic.registration.internal.fsm.actions.BaseRegistrationAction
import com.uretouch.feature.auth.logic.registration.internal.fsm.state.RegistrationState
import ru.kontur.mobile.visualfsm.Feature
import ru.kontur.mobile.visualfsm.GenerateTransitionsFactory

@GenerateTransitionsFactory
internal class RegistrationFeature(
    initialState: RegistrationState,
    private val asyncWorker: RegistrationAsyncWorker,
) : InstanceKeeper.Instance, Feature<RegistrationState, BaseRegistrationAction>(
    initialState = initialState,
    asyncWorker = asyncWorker,
    transitionsFactory = GeneratedRegistrationFeatureTransitionsFactory(),
) {
    override fun onDestroy() {
        asyncWorker.unbind()
    }
}