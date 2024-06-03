package com.uretouch.feature.history.logic.generationDetail.internal.fsm.actions

import com.uretouch.domain.generations.model.Generation
import com.uretouch.feature.history.logic.generationDetail.internal.fsm.state.GenerationDetailState
import ru.kontur.mobile.visualfsm.Edge
import ru.kontur.mobile.visualfsm.Transition

internal class GenerationDetailHandleLoadedSuccess(
    private val generation: Generation,
) : BaseGenerationDetailAction() {

    @Edge("LoadedSuccess")
    inner class Blank : Transition<GenerationDetailState.AsyncWorkerState.Loading, GenerationDetailState.Loaded>() {
        override fun transform(state: GenerationDetailState.AsyncWorkerState.Loading): GenerationDetailState.Loaded {
            return GenerationDetailState.Loaded(
                generation = generation
            )
        }
    }
}