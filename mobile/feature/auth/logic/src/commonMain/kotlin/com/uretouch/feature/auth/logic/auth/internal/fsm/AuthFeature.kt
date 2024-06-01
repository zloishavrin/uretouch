package com.uretouch.feature.auth.logic.auth.internal.fsm

import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import com.uretouch.feature.auth.logic.auth.internal.fsm.actions.BaseAuthAction
import com.uretouch.feature.auth.logic.auth.internal.fsm.state.AuthState
import ru.kontur.mobile.visualfsm.Feature
import ru.kontur.mobile.visualfsm.GenerateTransitionsFactory

@GenerateTransitionsFactory
internal class AuthFeature(
    initialState: AuthState,
    private val asyncWorker: AuthAsyncWorker,
) : InstanceKeeper.Instance, Feature<AuthState, BaseAuthAction>(
    initialState = initialState,
    asyncWorker = asyncWorker,
    transitionsFactory = GeneratedAuthFeatureTransitionsFactory()
) {
    override fun onDestroy() {
        asyncWorker.unbind()
    }
}