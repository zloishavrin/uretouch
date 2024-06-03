package com.uretouch.feature.history.logic.generationDetail.internal.fsm.actions

import com.uretouch.feature.history.logic.generationDetail.internal.fsm.state.GenerationDetailState
import ru.kontur.mobile.visualfsm.Edge
import ru.kontur.mobile.visualfsm.Transition

internal class GenerationDetailHandleLoadedFail() : BaseGenerationDetailAction() {

    @Edge("LoadedFail")
    inner class Blank : Transition<GenerationDetailState.AsyncWorkerState.Loading, GenerationDetailState.Error>() {
        override fun transform(state: GenerationDetailState.AsyncWorkerState.Loading): GenerationDetailState.Error {
            return GenerationDetailState.Error(id = state.id)
        }
    }
}