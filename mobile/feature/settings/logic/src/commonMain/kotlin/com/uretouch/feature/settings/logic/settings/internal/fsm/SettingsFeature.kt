package com.uretouch.feature.settings.logic.settings.internal.fsm

import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import com.uretouch.feature.settings.logic.settings.internal.fsm.actions.BaseSettingsAction
import com.uretouch.feature.settings.logic.settings.internal.fsm.state.SettingsState
import ru.kontur.mobile.visualfsm.Feature
import ru.kontur.mobile.visualfsm.GenerateTransitionsFactory

@GenerateTransitionsFactory
internal class SettingsFeature(
    initialState: SettingsState,
    private val asyncWorker: SettingsAsyncWorker,
) : InstanceKeeper.Instance, Feature<SettingsState, BaseSettingsAction>(
    initialState = initialState,
    asyncWorker = asyncWorker,
    transitionsFactory = GeneratedSettingsFeatureTransitionsFactory(),
) {
    override fun onDestroy() {
        asyncWorker.unbind()
    }
}