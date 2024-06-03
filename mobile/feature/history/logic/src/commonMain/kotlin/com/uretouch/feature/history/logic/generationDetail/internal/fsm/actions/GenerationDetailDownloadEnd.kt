package com.uretouch.feature.history.logic.generationDetail.internal.fsm.actions

import com.uretouch.feature.history.logic.generationDetail.internal.fsm.state.GenerationDetailState
import ru.kontur.mobile.visualfsm.Edge
import ru.kontur.mobile.visualfsm.Transition

internal class GenerationDetailDownloadEnd() : BaseGenerationDetailAction() {

    @Edge("DownloadEnd")
    inner class Blank : Transition<GenerationDetailState.AsyncWorkerState.DownloadGeneration, GenerationDetailState.Loaded>() {
        override fun transform(state: GenerationDetailState.AsyncWorkerState.DownloadGeneration): GenerationDetailState.Loaded {
            return state.sourceState
        }
    }
}