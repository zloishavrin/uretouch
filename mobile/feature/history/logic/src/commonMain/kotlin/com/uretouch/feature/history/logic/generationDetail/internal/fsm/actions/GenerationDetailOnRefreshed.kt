package com.uretouch.feature.history.logic.generationDetail.internal.fsm.actions

import com.uretouch.feature.history.logic.generationDetail.internal.fsm.state.GenerationDetailState
import ru.kontur.mobile.visualfsm.Edge
import ru.kontur.mobile.visualfsm.Transition

internal class GenerationDetailOnRefreshed() : BaseGenerationDetailAction() {

    @Edge("OnRefreshed")
    inner class Blank : Transition<GenerationDetailState.Error, GenerationDetailState.AsyncWorkerState.Loading>() {
        override fun transform(state: GenerationDetailState.Error): GenerationDetailState.AsyncWorkerState.Loading {
            return GenerationDetailState.AsyncWorkerState.Loading(id = state.id)
        }
    }
}