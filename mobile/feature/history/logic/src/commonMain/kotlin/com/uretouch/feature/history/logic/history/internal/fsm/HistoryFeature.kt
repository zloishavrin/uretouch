package com.uretouch.feature.history.logic.history.internal.fsm

import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import com.uretouch.feature.history.logic.history.internal.fsm.actions.BaseHistoryAction
import com.uretouch.feature.history.logic.history.internal.fsm.state.HistoryState
import ru.kontur.mobile.visualfsm.Feature
import ru.kontur.mobile.visualfsm.GenerateTransitionsFactory

@GenerateTransitionsFactory
internal class HistoryFeature(
    initialState: HistoryState,
    private val asyncWorker: HistoryAsyncWorker,
) : InstanceKeeper.Instance, Feature<HistoryState, BaseHistoryAction>(
    initialState = initialState,
    asyncWorker = asyncWorker,
    transitionsFactory = GeneratedHistoryFeatureTransitionsFactory(),
) {
    override fun onDestroy() {
        asyncWorker.unbind()
    }
}