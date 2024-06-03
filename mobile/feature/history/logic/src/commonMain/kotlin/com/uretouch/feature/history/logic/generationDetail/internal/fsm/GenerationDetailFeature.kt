package com.uretouch.feature.history.logic.generationDetail.internal.fsm

import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import com.uretouch.feature.history.logic.generationDetail.internal.fsm.actions.BaseGenerationDetailAction
import com.uretouch.feature.history.logic.generationDetail.internal.fsm.state.GenerationDetailState
import ru.kontur.mobile.visualfsm.Feature
import ru.kontur.mobile.visualfsm.GenerateTransitionsFactory

@GenerateTransitionsFactory
internal class GenerationDetailFeature(
    initialState: GenerationDetailState,
    private val asyncWorker: GenerationDetailAsyncWorker,
) : InstanceKeeper.Instance, Feature<GenerationDetailState, BaseGenerationDetailAction>(
    initialState = initialState,
    asyncWorker = asyncWorker,
    transitionsFactory = GeneratedGenerationDetailFeatureTransitionsFactory(),
) {
    override fun onDestroy() {
        asyncWorker.unbind()
    }
}