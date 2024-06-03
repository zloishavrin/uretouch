package com.uretouch.feature.history.logic.generationDetail.internal.fsm.actions

import com.uretouch.feature.history.logic.generationDetail.internal.fsm.state.GenerationDetailState
import ru.kontur.mobile.visualfsm.Edge
import ru.kontur.mobile.visualfsm.Transition

internal class GenerationDetailOnDownloadGeneration(
    private val url: String,
) : BaseGenerationDetailAction() {

    @Edge("OnDownloadGeneration")
    inner class Blank : Transition<GenerationDetailState.Loaded, GenerationDetailState.AsyncWorkerState.DownloadGeneration>() {
        override fun transform(state: GenerationDetailState.Loaded): GenerationDetailState.AsyncWorkerState.DownloadGeneration {
            return GenerationDetailState.AsyncWorkerState.DownloadGeneration(
                sourceState = state,
                urls = listOf(url)
            )
        }
    }
}